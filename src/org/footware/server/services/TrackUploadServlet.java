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
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("serial")
public class TrackUploadServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("attributes");
		Enumeration atts = req.getAttributeNames();
		while(atts.hasMoreElements()) {
			System.out.println((String) atts.nextElement());
		}
		
		File baseDirectory = createDirectories("testUser");

		System.out.println(req.getCharacterEncoding());
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getParameterMap().keySet());
		// Check that we have a file upload request
		// boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		System.out.println(req.getContentType());
		System.out.println(req.isRequestedSessionIdValid());
		// System.out.println(isMultipart);

		// if (isMultipart) {
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("tmp"));


		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			// Process a file upload
			System.out.println(item.getFieldName());
			if (!item.isFormField() && item.getFieldName().equals("file")) {
				System.out.println(item.getContentType());
				System.out.println(item.getSize());
				String fieldName = item.getFieldName();
				String fileName = item.getName();
				String contentType = item.getContentType();
				boolean isInMemory = item.isInMemory();
				long sizeInBytes = item.getSize();
				System.out.println(fieldName);
				System.out.println(fileName);
				System.out.println(contentType);
				System.out.println(isInMemory);
				System.out.println(sizeInBytes);
				System.out.println(baseDirectory.getAbsolutePath());
				File uploadedFile = new File(baseDirectory.getAbsolutePath()
						+ "/import.gpx");
				try {
					item.write(uploadedFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// ArrayList files = (ArrayList) req
		// .getAttribute("org.mortbay.servlet.MultiPartFilter.files");

		// if (files != null) {
		// System.out.println("yep");
		// for (int x = 0; x < files.size(); x++) {
		// File file1 = (File) files.get(x);
		// File outputFile = new File("outputfile" + (x + 1));
		// file1.renameTo(outputFile);
		// System.out.println(outputFile.getName());
		// }
		//
		// StringBuffer buff = new StringBuffer();
		//
		// File file1 = (File) req.getAttribute("file");
		//
		// if (file1 == null || !file1.exists()) {
		// buff.append("File does not exist");
		// } else if (file1.isDirectory()) {
		// buff.append("File is a directory");
		// } else {
		// File outputFile = new File(req.getParameter("file"));
		// file1.renameTo(outputFile);
		// }
		// } else {
		// System.out.println("nope");
		// }
	}

	private File createDirectories(String user) {

		File tmpDirectory = new File("tmp");
		if (!tmpDirectory.exists()) {
			tmpDirectory.mkdir();
		}
		tmpDirectory.setWritable(true);

		// Check if import directory exists
		File importDirectory = new File("import");
		if (!importDirectory.exists()) {
			importDirectory.mkdir();
		}

		// Check if user directoy exists
		File userDirectory = new File("import/" + user);
		if (!userDirectory.exists()) {
			userDirectory.mkdir();
		}
		return userDirectory;

	}
	
}
