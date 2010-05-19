package org.footware.shared.dto;

import java.io.Serializable;
import java.util.Date;


public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	
	private TrackDTO track;
	private UserDTO user;
	private String text;
	private Date time;
	
	protected CommentDTO() {}
	
	public CommentDTO(String comment, UserDTO user) {
		this.text = comment;
		this.user = user;
	}
	
	protected long getId() {
		return id;
	}
	
	public TrackDTO getTrack() {
		return track;
	}
//	public void setTrack(TrackDTO track) {
//		this.track = track;
//	}
	public UserDTO getUser() {
		return user;
	}
//	public void setUser(UserDTO user) {
//		this.user = user;
//	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
