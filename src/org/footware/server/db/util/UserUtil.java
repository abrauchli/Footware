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

package org.footware.server.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.footware.server.db.User;

/**
 * Utility class for user specific operations
 */
public class UserUtil {

	/**
	 * Digest a password to compute its (MD5) hash for storage
	 * 
	 * @param password
	 *            the password string to hash
	 * @return the password's hashed string, null if the MD5 algorithm isn't
	 *         available
	 */
	public static String getPasswordHash(String password) {
		return DigestUtils.md5Hex(password);
	}

	// @NamedQuery(name = "users.getByEmail", query =
	// "FROM User u WHERE u.email = :email"),
	// // Get all users
	// @NamedQuery(name = "users.getAll", query = "FROM User"),
	// //Get user from email/password pair
	// @NamedQuery(name = "users.getIfValid", query =
	// "FROM User u WHERE u.email = :email AND u.password = :password"),
	// //Get user from email/password pair
	// @NamedQuery(name = "users.getPublicTracks", query =
	// "FROM Track t WHERE t.user = :user AND t.publicity = 5")

	/**
	 * Gets a single user by email
	 * 
	 * @param email
	 *            the email belonging to the user
	 * @return user object if email is valid, null otherwise
	 */
	public static User getByEmail(String email) {
		long id = -1;
		try {
			id = DB.queryLong("SELECT id FROM User u WHERE u.email = '" + email+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new User(id);
	}

	/**
	 * Gets all users registered with the system
	 * 
	 * @return collection of all users
	 */
	public static List<User> getAll() {
		ResultSet r = null;
		try {
			r = DB.query("SELECT id FROM User u");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LinkedList<User> res = new LinkedList<User>();
		if (r != null) {
			int i = 1;
			try {
				while(r.next()) {
					res.add(new User(r.getLong(i)));
					i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return res;
	}

	/**
	 * Gets a single user by email and password hash
	 * 
	 * @param email
	 *            the email belonging to the user
	 * @param pw_hash
	 *            the password hash to try as password
	 * @return user object if email/pw-hash pair is valid, null otherwise
	 */
	public static User getIfValid(String email, String pw_hash) {
		long id = -1;
		try {
			id = DB.queryLong("SELECT id FROM User u WHERE u.email = '" + email + "' AND u.password = '" + pw_hash+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new User(id);
	}
}
