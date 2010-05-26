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
