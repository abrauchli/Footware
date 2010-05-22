package org.footware.server.services;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.footware.shared.dto.TrackDTO;

@SuppressWarnings("serial")
public class TrackUploadServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletFileUpload upload = new ServletFileUpload();
		// TODO get username
		String user = "1234";
		try {
			FileItemIterator iter = upload.getItemIterator(req);
			while (iter.hasNext()) {

				FileItemStream item = iter.next();
				if (item.getFieldName().equals("file")) {
					InputStream stream = item.openStream();
					// TODO do stuff here
					//TODO rene hier kommt der fileupload hin. Das File ist in stream
					TrackDTO track = new TrackDTO();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
