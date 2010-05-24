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

	/**
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the email address of the associated user
	 * @return user's email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the associated user
	 * @param email new email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the full name of the user
	 * @return user's full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name of the user
	 * @param fullName new user's name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the user's password hash
	 * @return user's password hash
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password (in clear text)
	 * @param password new password (in clear text)
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets whether the user has administrative privileges
	 * @return boolean true if user is admin, false otherwise
	 */
	public boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * Changes the user's administrative privileges
	 * @param isAdmin true means the user becomes an admin, false degrades him to an ordinary user
	 */
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * Gets all tracks associated with this user
	 * @return all user's tracks
	 */
	public Set<TrackDTO> getTracks() {
		return tracks;
	}
	
	/**
	 * Adds a new track to this user's track collection
	 * @param track track to add
	 */
	public void addTrackDTO(TrackDTO track) {
		if (tracks == null)
			tracks = new HashSet<TrackDTO>();

		tracks.add(track);
	}
	
	/**
	 * Removes a track from this user's track collection
	 * @param track track to remove
	 */
	public void removeTrackDTO(TrackDTO track) {
		if (tracks != null) {
			tracks.remove(track);
		}
	}

	/**
	 * Gets the tags associated with this user
	 * @return all tags of this user
	 */
	public Set<TagDTO> getTags() {
		return tags;
	}
}
