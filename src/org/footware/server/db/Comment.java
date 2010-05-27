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

package org.footware.server.db;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.footware.server.db.util.DB;
import org.footware.shared.dto.CommentDTO;

/**
 * Class for ER mapping of comments
 */
public class Comment extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getTable() {
		return "comment";
	}
	
	//@ManyToOne(fetch=FetchType.LAZY)
	//@Column(nullable=false)
	private Track track;
	//@ManyToOne(fetch=FetchType.LAZY)
	//@Column(nullable=false)
	private User user;
	//@Column(length=256,nullable=false)
	private String text;

	//@Column(nullable=false)
	private Date time;
	
	protected Comment() {
	}

	/**
	 * Create a new object from the db id
	 * @param id
	 */
	public Comment(Long id) {
		this.id = id;
	}

	/**
	 * Create a new comment for persistence
	 * @param comment The comment object received from the frontend
	 */
	public Comment(CommentDTO comment) {
		this.track = new Track(comment.getTrack());
		this.user = new User(comment.getUser());
		this.text = comment.getText();
		this.time = comment.getTime();
	}

	/**
	 * Create a new comment for persistence
	 * @param comment Text for the comment
	 * @param user User posting the comment
	 */
	public Comment(String comment, User user) {
		this.text = comment;
		this.user = user;
	}
	
	@Override
	public void store() {
		String cols = "comment,track_id,user_id,time";
		text = text.replace("\\", "\\\\");
		text = text.replace("'", "\\'");
		if (time == null)
			time = new Date();
		String timefmt = DB.sqlFormatDate(time);
		String vals = String.format("'%s',%d,%d,'%s'", text, track.getId(), user.getId(), timefmt);
		try {
			DB.insert("INSERT INTO "+ getTable() +" SET ("+ cols +") VALUES ("+ vals +")");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Gets the track for which this comment is written
	 * @return corresponding track for this comment
	 */
	public Track getTrack() {
		return track;
	}
	
//	public void setTrack(Track track) {
//		this.track = track;
//	}

	/**
	 * Gets the author of the comment
	 */
	public User getUser() {
		return new User(getLongValue("user_id", defaultId));
	}
	
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	/**
	 * Gets the comment text
	 */
	public String getText() {
		return getStrValue("text", null);
	}
	
	/**
	 * Set the comment text
	 * @param text new comment text
	 */
	public void setText(String text) {
		setStrValue("text", text);
	}

	/**
	 * Gets the comment date/time
	 * @return the date (and time) the comment was written
	 */
	public Date getTime() {
		Timestamp t = getTimestampValue("time", null);
		if (t != null)
			return new Date(t.getTime());
		return null;
	}

	/**
	 * Set the comment time
	 * @param set the time this comment was written
	 */
	public void setTime(Date time) {
		setTimestampValue("time",new Timestamp(time.getTime()));
	}

	/**
	 * Gets a CommentDTO representing this current object's state
	 * @return CommentDTO representing this object's state
	 */
	public CommentDTO getCommentDTO() {
		CommentDTO c = new CommentDTO(text, user.getUserDTO(), track.getTrackDTO());
		c.setTime(time);
		return c;
	}

}
