package org.footware.server.db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.footware.shared.dto.UserDTO;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(length=128,unique=true,nullable=false)
	private String email;
	@Column(length=64)
	private String fullName;
	@Column(length=32)
	private char[] password;
	private boolean isAdmin;
	@OneToMany(fetch=FetchType.LAZY)
	private Set<Track> tracks;
	@OneToMany(fetch=FetchType.LAZY)
	private Set<Tag> tags;
	
	protected User() {}
	
	public User(String email, String password) {
		this.email = email;
		this.password = (new org.apache.catalina.util.MD5Encoder()).encode(password.getBytes()).toCharArray();
	}
	
	public User(UserDTO user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.fullName = user.getFullName();
		this.password = (new org.apache.catalina.util.MD5Encoder()).encode(user.getPassword().getBytes()).toCharArray();
		this.isAdmin = user.getIsAdmin();
	}
	
	protected long getId() {
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
