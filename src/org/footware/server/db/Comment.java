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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.footware.shared.dto.CommentDTO;
import org.hibernate.Hibernate;

/**
 * Class for ER mapping of comments
 */
@Entity
public class Comment extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	//@Column(nullable=false)
	private Track track;
	
	@ManyToOne(fetch=FetchType.LAZY)
	//@Column(nullable=false)
	private User user;
	
	@Column(length=256,nullable=false)
	private String text;
	
	@Column(nullable=false)
	private Date time;
	
	protected Comment() {}

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

	/**
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	protected long getId() {
		return id;
	}

	/**
	 * Gets the track for which this comment is written
	 * @return corresponding track for this comment
	 */
	public Track getTrack() {
		Hibernate.initialize(track);
		return track;
	}
	
//	public void setTrack(Track track) {
//		this.track = track;
//	}

	/**
	 * Gets the author of the comment
	 */
	public User getUser() {
		Hibernate.initialize(user);
		return user;
	}
	
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	/**
	 * Gets the comment text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Set the comment text
	 * @param text new comment text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the comment date/time
	 * @return the date (and time) the comment was written
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Set the comment time
	 * @param set the time this comment was written
	 */
	public void setTime(Date time) {
		this.time = time;
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
