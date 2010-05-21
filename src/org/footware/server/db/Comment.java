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

/**
 * Class for ER mapping of comments
 */
@Entity
public class Comment implements Serializable {

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
		return track;
	}
	
//	public void setTrack(Track track) {
//		this.track = track;
//	}

	/**
	 * Gets the author of the comment
	 */
	public User getUser() {
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

}
