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

package org.footware.shared.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO Class for comments
 */
public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private TrackDTO track;
	private UserDTO user;
	private String text;
	private Date time;

	protected CommentDTO() {
	}

	/**
	 * Create a new comment (thought for persistence)
	 * 
	 * @param comment
	 *            Text for the comment
	 * @param user
	 *            User posting the comment
	 */
	public CommentDTO(String comment, UserDTO user, TrackDTO track) {
		this.text = comment;
		this.user = user;
		this.track = track;
	}

	/**
	 * Gets the id of the corresponding DB row
	 * 
	 * @return the ID of the row in the DB
	 */
	protected long getId() {
		return id;
	}

	/**
	 * Gets the track for which this comment is written
	 * 
	 * @return corresponding track for this comment
	 */
	public TrackDTO getTrack() {
		return track;
	}

	// public void setTrack(TrackDTO track) {
	// this.track = track;
	// }

	/**
	 * Gets the author of the comment
	 */
	public UserDTO getUser() {
		return user;
	}

	// public void setUser(UserDTO user) {
	// this.user = user;
	// }

	/**
	 * Gets the comment text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Set the comment text
	 * 
	 * @param text
	 *            new comment text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the comment date/time
	 * 
	 * @return the date (and time) the comment was written
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Set the comment time
	 * 
	 * @param set
	 *            the time this comment was written
	 */
	public void setTime(Date time) {
		this.time = time;
	}
}
