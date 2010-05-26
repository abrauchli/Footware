/**
 * 
 */
package org.footware.server.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.footware.server.db.HibernateUtil;
import org.footware.server.db.Track;
import org.footware.server.db.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Serverlet for downloading of saved Tracks
 * 
 * File download code adapted from: The File servlet for serving from absolute
 * path.
 * 
 * @author BalusC, rene
 * @link http://balusc.blogspot.com/2007/07/fileservlet.html
 */
public class TrackDownloadServlet extends HttpServlet {

	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	private static final long serialVersionUID = 1L;
	private Logger logger;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		logger = LoggerFactory.getLogger(TrackDownloadServlet.class);

		String userid = req.getParameter("userid");
		String trackid = req.getParameter("trackid");

		Boolean hasRight;

		if (userid != null && trackid != null) {

			Transaction tx = null;
			try {
				Session session = HibernateUtil.getSessionFactory()
						.getCurrentSession();
				User user = (User) session.load(User.class, new Long(userid));
				Track track = (Track) session.load(Track.class, new Long(
						trackid));

				// Check if user is allowed to download track
				// TODO: Only privacy 0 and 5 are implemented yet

				// Check if privacy is private
				if (track.getPublicity() == 0) {
					hasRight = false;
					for (Track usertrack : user.getTracks()) {
						if (usertrack.getId() == track.getId()) {
							hasRight = true;
							break;
						}
					}

					// If user
				} else {
					hasRight = true;
				}

				if (hasRight) {
					// Decode the file name (might contain spaces and on) and
					// prepare file object.
					File file = new File(track.getPath());

					// Check if file actually exists in filesystem.
					if (!file.exists()) {
						// Do your thing if the file appears to be non-existing.
						// Throw an exception, or send 404, or show
						// default/warning page, or just ignore it.
						resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
						return;
					}

					// Get content type by filename.
					String contentType = getServletContext().getMimeType(
							file.getName());

					// If content type is unknown, then set the default value.
					// For all content types, see:
					// http://www.w3schools.com/media/media_mimeref.asp
					// To add new content types, add new mime-mapping entry in
					// web.xml.
					if (contentType == null) {
						contentType = "application/octet-stream";
					}

					// Init servlet response.
					resp.reset();
					resp.setBufferSize(DEFAULT_BUFFER_SIZE);
					resp.setContentType(contentType);
					resp.setHeader("Content-Length", String.valueOf(file
							.length()));
					resp.setHeader("Content-Disposition",
							"attachment; filename=\"" + file.getName() + "\"");

					// Prepare streams.
					BufferedInputStream input = null;
					BufferedOutputStream output = null;

					try {
						// Open streams.
						input = new BufferedInputStream(new FileInputStream(
								file), DEFAULT_BUFFER_SIZE);
						output = new BufferedOutputStream(resp
								.getOutputStream(), DEFAULT_BUFFER_SIZE);

						// Write file contents to response.
						byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
						int length;
						while ((length = input.read(buffer)) > 0) {
							output.write(buffer, 0, length);
						}
					} finally {
						// Gently close streams.
						close(output);
						close(input);
					}
				}

			} catch (RuntimeException e) {
				if (tx != null && tx.isActive()) {
					try {
						// Second try catch as the rollback could fail as well
						tx.rollback();
					} catch (HibernateException e1) {
						logger.debug("Error rolling back transaction");
					}
					// throw again the first exception
					throw e;
				}
			}

		} else {
			logger.error("userid or trackid is null: userid" + userid
					+ " trackid" + trackid);
			resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or mail
				// it.
				e.printStackTrace();
			}
		}
	}

}
