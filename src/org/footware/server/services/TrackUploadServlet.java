/*
 * Copyright 2010 Andreas Brauchli, René Buffat, Florian Widmer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.footware.server.services;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import org.footware.server.db.Track;
import org.footware.server.gpx.GPXImport;
import org.footware.shared.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class TrackUploadServlet extends HttpServlet {

	private Logger logger;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		logger = LoggerFactory.getLogger(TrackUploadServlet.class);

		UserDTO user = new UserDTO();
		user.setEmail("test@user.ch");

		logger.info("User: " + user);
		String userDirectoryString = user.getEmail().replace("@", "_at_");

		File baseDirectory = initFileStructure(userDirectoryString);

		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		// Init fields of form
		File uploadedFile = null;
		String notes = null;
		String name = null;
		int privacy = 0;
		Boolean comments = false;

		if (isMultipart) {

			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File("tmp"));
			factory.setSizeThreshold(10000000);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Create a progress listener
			ProgressListener progressListener = new ProgressListener() {
				public void update(long pBytesRead, long pContentLength,
						int pItems) {
					logger.info("We are currently reading item " + pItems);
					if (pContentLength == -1) {
						logger.info("So far, " + pBytesRead
								+ " bytes have been read.");
					} else {
						logger.info("So far, " + pBytesRead + " of "
								+ pContentLength + " bytes have been read.");
					}
				}
			};
			upload.setProgressListener(progressListener);

			// Parse the request
			List<FileItem> items;
			try {
				items = upload.parseRequest(req);

				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();

					// Process a file upload

					if (!item.isFormField()
							&& item.getFieldName().equals("file")) {

						// Save file to disk
						String fileName = item.getName();
						if (fileName == null) {
							logger.info("empty file name");
							break;
						}
						logger.info("received file:" + fileName);

						if (fileName != null) {
							fileName = FilenameUtils.getName(fileName);
						}

						uploadedFile = getSavePath(baseDirectory
								.getAbsolutePath(), fileName);
						logger.debug(uploadedFile.getAbsolutePath());
						item.write(uploadedFile);

					} else if (item.isFormField()
							&& item.getFieldName().equals("notes")) {
						notes = item.getString();
						logger.debug("notes" + ": " + item.getString());
					} else if (item.isFormField()
							&& item.getFieldName().equals("comments")) {
						if (item.getString().equals("on")) {
							comments = true;
						} else {
							comments = false;
						}
						logger.debug("comments" + ": " + item.getString());

					} else if (item.isFormField()
							&& item.getFieldName().equals("privacy")) {
						String priv = item.getString();
						if (priv.equals("public")) {
							privacy = 5;
						} else if (priv.equals("private")) {
							privacy = 0;
						} else {
							privacy = 0;
						}
						logger.debug("privacy" + ": " + item.getString());
					} else if (item.isFormField()
							&& item.getFieldName().equals("name")) {
						name = item.getString();
						logger.debug("name" + ": " + item.getString());
					}
				}
			} catch (FileUploadException e1) {
				logger.error("File upload unsucessful", e1);
				e1.printStackTrace();
			} catch (Exception e) {
				logger.error("File upload unsucessful", e);
				e.printStackTrace();
			}
		}

		// Start GPX Import
		if (uploadedFile != null) {
			GPXImport importer = new GPXImport(user, notes, comments, privacy,
					name);
			importer.importTrack(uploadedFile);
			
			//Add meta information to track
			for( Track track : importer.getTracks()) {
				
			}
			
		}
	}

	private File getSavePath(String base, String fileName) {
		File file = new File(base + "/" + fileName);
		if (file.exists()) {
			String baseName = fileName.substring(0, fileName.length() - 3);
			logger.debug("baseName: " + baseName);
			String suffix = fileName.substring(fileName.length() - 3, fileName
					.length());
			logger.debug("suffix: " + suffix);

			// If the file is already here, the file name is a_name.gpx
			if (suffix.equals("gpx")) {
				suffix += "_001";
				logger.debug("suffix: " + suffix);

				// If the fileName was already uploaded more than to times, the
				// file has the form a_name.gpx_xxx
			} else if (fileName.substring(fileName.length() - 4,
					fileName.length() - 3).equals("_")) {

				// We increment the suffix number by 1 to generate a new unique
				// file name
				int nr = Integer.parseInt(suffix);
				logger.debug("suffix int: " + nr);

				// If we have already 999 with the same file name, we add a new
				// round at the end
				if (nr == 999) {
					suffix = suffix + "_001";
				} else {
					nr++;
					suffix = String.format("%03d", nr);
				}
				logger.debug("suffix: " + suffix);
			}
			logger.debug("new name: " + baseName + suffix);
			return getSavePath(base, baseName + suffix);
		}
		return file;
	}

	private File initFileStructure(String user) {

		File tmpDirectory = new File("tmp");
		if (!tmpDirectory.exists()) {
			logger.debug("Create 'tmp' directory");
			tmpDirectory.mkdir();
		}
		tmpDirectory.setWritable(true);

		// Check if import directory exists
		File importDirectory = new File("import");
		if (!importDirectory.exists()) {
			logger.debug("Create 'import' directory");
			importDirectory.mkdir();
		}

		// Check if user directoy exists
		File userDirectory = new File(importDirectory.getAbsolutePath() + "/"
				+ user);
		if (!userDirectory.exists()) {
			logger.debug("Create '" + importDirectory.getAbsolutePath() + "/"
					+ user + "' directory");
			userDirectory.mkdir();
		}
		return userDirectory;

	}

}
