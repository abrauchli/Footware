package org.footware.shared.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TrackDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private UserDTO user;
	private String filename;
	private String path;
	private String notes;
	private int publicity;
	private boolean commentsEnabled;
	private int trackpoints;
	private int length;
	private Date startTime;
	private Map<String, UserDTO> comments;
	
	public long getId() {
		return id;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
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
	
	public Map<String,UserDTO> getComments() {
		return comments;
	}
	public void addComment(String comment, UserDTO user) {
		if (comments == null)
			comments = new HashMap<String, UserDTO>();
		comments.put(comment, user);
	}
}
