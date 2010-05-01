package org.footware.server.db;

import java.io.Serializable;

public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Track track;
	private User user;
	private String text;
	
	protected Comment() {}
	
	public Comment(String comment, User user) {
		this.text = comment;
		this.user = user;
	}
	
	public Track getTrack() {
		return track;
	}
	public void setTrack(Track track) {
		this.track = track;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
