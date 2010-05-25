package org.footware.client;

import org.footware.shared.dto.UserDTO;

/**
 * Helper class to store session specific data
 */
public class Session {
	/**
	 * Stores the currently logged in user. null if none
	 */
	private static UserDTO user;

	/**
	 * Gets the currently logged in user. null if none
	 */
	public static UserDTO getUser() {
		return user;
	}
	
	/**
	 * Sets the currently logged in user. null for none
	 * @param user user to be marked as logged in. null for none
	 */
	public static void setUser(UserDTO user) {
		Session.user = user;
	}
}
