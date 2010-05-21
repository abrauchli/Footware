package org.footware.server.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.footware.shared.dto.CommentDTO;
import org.footware.shared.dto.TrackDTO;
import org.hibernate.annotations.ManyToAny;

/**
 * Class for ER mapping of Tracks
 */
@Entity
public class Track implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	//@Column(nullable=false)
	private User user;
	
	@Column(length=128,nullable=false)
	private String filename;
	
	@Column(length=128)
	private String path;
	
	@Column(length=256)
	private String notes;
	
	//@Enumerated()
	private int publicity;
	
	private boolean commentsEnabled;
	private int trackpoints;
	private int length;
	private Date startTime;
	
	@ManyToAny(metaColumn=@Column(name="comment_id"), fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	protected Track() {}
	
	public Track(User u, String filename, String path) {
		this.user = u;
		this.filename = filename;
		this.path = path;
	}
	
	public Track(TrackDTO track) {
		this.id = track.getId();
		this.user = new User(track.getUser());
		this.filename = track.getFilename();
		this.path = track.getPath();
		this.notes = track.getNotes();
		this.publicity = track.getPublicity();
		this.commentsEnabled = track.isCommentsEnabled();
		this.trackpoints = track.getTrackpoints();
		this.length = track.getLength();
		this.startTime = track.getStartTime();
		List<CommentDTO> comments = track.getComments();
		if (comments != null) {
			this.comments = new ArrayList<Comment>();
			for (CommentDTO comment : comments)
				this.comments.add(new Comment(comment));
		}
	}

	/**
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Gets the user belonging to this track
	 * @return this track's user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Sets the user owning this track
	 * @param user new user owning this track
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Gets the tracks original filename (as uploaded)
	 * @return the tracks original filename
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * Sets the tracks filename (as uploaded)
	 * @param filename new filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * Gets the authors track notes
	 * @return authors track notes
	 */
	public String getNotes() {
		return notes;
	}
	
	/**
	 * Sets the authors track notes
	 * @param notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * Gets the publicity (permission) level for this track
	 * @return the publicity (permission) level for this track
	 */
	public int getPublicity() {
		return publicity;
	}
	
	/**
	 * Sets the publicity (permission) level for this track
	 * @param publicity new publicity (permission) level for this track
	 */
	public void setPublicity(int publicity) {
		this.publicity = publicity;
	}
	
	/**
	 * Gets whether commenting this track is enabled
	 * @return true if enabled
	 */
	public boolean isCommentsEnabled() {
		return commentsEnabled;
	}
	
	/**
	 * Sets whether commenting this track is enabled
	 * @param commentsEnabled true if enabled (existing comments will not be deleted if disabled)
	 */
	public void setCommentsEnabled(boolean commentsEnabled) {
		this.commentsEnabled = commentsEnabled;
	}
	
	/**
	 * Gets the number of track points for this track
	 * @return number of track points for this track
	 */
	public int getTrackpoints() {
		return trackpoints;
	}
	
	/**
	 * Sets the number of track points for this track
	 * Should probably not be used from the client side
	 * @param trackpoints number of track points on this track
	 */
	public void setTrackpoints(int trackpoints) {
		this.trackpoints = trackpoints;
	}
	
	/**
	 * Gets the length in meters (TODO: Check unit) of this track
	 * @return the length in meters of this track
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Sets the length (in meters) of this track
	 * Should probably not be used from the client side
	 * @param length new length in meters for this track
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * Gets the time of the first track point in this track
	 * @return the time of the first track point in this track
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * Sets the time of the first track point in this track
	 * Should probably not be used from the client side
	 * @param startTime time of the first track point in this track
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Gets all comments for this track
	 * @return List of all comments for this track
	 */
	public List<Comment> getComments() {
		return comments;
	}
	
	/**
	 * Adds a new comment to this track
	 * Note: the user writing the comment is registered in the comment object
	 * @param comment comment to be added to this track
	 */
	public void addComment(Comment comment) {
		if (comments == null)
			comments = new ArrayList<Comment>();
		comments.add(comment);
	}
	
}
