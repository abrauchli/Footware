package org.footware.server.db;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.UserDTO;

public class Track implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private User user;
	private String filename;
	private String path;
	private String notes;
	private int publicity;
	private boolean commentsEnabled;
	private int trackpoints;
	private int length;
	private Date startTime;
	private Set<Comment> comments;
	
	public Track() {}
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
		Map<String,UserDTO> comments = track.getComments();
		if (comments != null) {
			this.comments = new HashSet<Comment>();
			for (String comment : comments.keySet())
				this.comments.add(new Comment(comment, new User(comments.get(comment))));
		}
	}
	
	public long getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getPublicity() {
		return publicity;
	}
	public void setPublicity(int publicity) {
		this.publicity = publicity;
	}
	public boolean isCommentsEnabled() {
		return commentsEnabled;
	}
	public void setCommentsEnabled(boolean commentsEnabled) {
		this.commentsEnabled = commentsEnabled;
	}
	public int getTrackpoints() {
		return trackpoints;
	}
	public void setTrackpoints(int trackpoints) {
		this.trackpoints = trackpoints;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Set<Comment> getComments() {
		return comments;
	}
	public void addComment(Comment comment) {
		if (comments == null)
			comments = new HashSet<Comment>();
		comments.add(comment);
	}
	
}
