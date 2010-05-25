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
import org.footware.server.gpx.GPXImport;
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


		String user = "testUser" + (int) (Math.random() * 100);
		logger.info("User: " + user);

		File baseDirectory = initFileStructure(user);

		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (isMultipart) {

			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File("tmp"));
			factory.setSizeThreshold(590841);

			// Create a new file upload handler
			
			ServletFileUpload upload = new ServletFileUpload(factory);

			//Create a progress listener
			ProgressListener progressListener = new ProgressListener(){
			   public void update(long pBytesRead, long pContentLength, int pItems) {
			      logger.info("We are currently reading item " + pItems);
			       if (pContentLength == -1) {
			           logger.info("So far, " + pBytesRead + " bytes have been read.");
			       } else {
			    	   logger.info("So far, " + pBytesRead + " of " + pContentLength
			                              + " bytes have been read.");
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
						
						//Save file to disk
						String fileName = item.getName();
						logger.info("received file:" + fileName);
						
						if (fileName != null) {
							fileName = FilenameUtils.getName(fileName);
						}
						
						String saveFileName = getSavePath(baseDirectory
								.getAbsolutePath(), fileName);
						
						logger.info(saveFileName);

						File uploadedFile = new File(saveFileName);
						item.write(uploadedFile);
					
						// Start GPX Import
						GPXImport importer = new GPXImport();
						importer.importTrack(uploadedFile);
						
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
	}
	
	private String getSavePath(String base, String fileName) {
		//TODO calculate save file name
		return base+"/"+fileName;
	}

	private File initFileStructure(String user) {

		File tmpDirectory = new File("tmp");
		if (!tmpDirectory.exists()) {
			logger.info("Create 'tmp' directory");
			tmpDirectory.mkdir();
		}
		tmpDirectory.setWritable(true);

		// Check if import directory exists
		File importDirectory = new File("import");
		if (!importDirectory.exists()) {
			logger.info("Create 'import' directory");
			importDirectory.mkdir();
		}

		// Check if user directoy exists
		File userDirectory = new File(importDirectory.getAbsolutePath() + "/" + user);
		if (!userDirectory.exists()) {
			logger.info("Create '"+ importDirectory.getAbsolutePath() + "/" + user +"' directory");
			userDirectory.mkdir();
		}
		return userDirectory;

	}
	


}
