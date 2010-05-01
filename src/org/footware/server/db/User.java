package org.footware.server.db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.footware.shared.dto.UserDTO;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String email;
	private String fullName;
	private char[] password;
	private boolean isAdmin;
	private Set<Track> tracks;
	private Set<Tag> tags;
	
	public User() {}
	public User(UserDTO user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.fullName = user.getFullName();
		this.password = user.getPassword();
		this.isAdmin = user.getIsAdmin();
	}
	
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

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public Set<Track> getTracks() {
		return tracks;
	}
	
	public void addTrack(Track track) {
		if (tracks == null)
			tracks = new HashSet<Track>();

		tracks.add(track);
	}
	
	public void removeTrack(Track track) {
		if (tracks != null) {
			tracks.remove(track);
		}
	}

	public Set<Tag> getTags() {
		return tags;
	}
}
