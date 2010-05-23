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
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("serial")
public class TrackUploadServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		createDirectories("testUser");
		
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (isMultipart) {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			try {
				List<FileItem> items = upload.parseRequest(req);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					// Process a file upload
					if (!item.isFormField()) {
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
						File uploadedFile = new File("import/testUser/import.gpx");
					    item.write(uploadedFile);
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private void createDirectories(String user) {
		
		//Check if import directory exists
		File importDirectory = new File("import");
		if(!importDirectory.exists()) {
			importDirectory.mkdir();
		}
		
		//Check if user directoy exists
		File userDirectory = new File("import/"+user);
		if(!userDirectory.exists()) {
			userDirectory.mkdir();
		}		
		
	}
}
