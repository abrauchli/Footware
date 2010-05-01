package org.footware.shared.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String email;
	private String fullName;
	private String password;
	private boolean isAdmin;
	private Set<TrackDTO> tracks;
	private Set<TagDTO> tags;
	
	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public Set<TrackDTO> getTracks() {
		return tracks;
	}
	
	public void addTrackDTO(TrackDTO track) {
		if (tracks == null)
			tracks = new HashSet<TrackDTO>();

		tracks.add(track);
	}
	
	public void removeTrackDTO(TrackDTO track) {
		if (tracks != null) {
			tracks.remove(track);
		}
	}

	public Set<TagDTO> getTags() {
		return tags;
	}
}
