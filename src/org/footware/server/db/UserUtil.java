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

package org.footware.server.db;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.catalina.util.MD5Encoder;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@NamedQueries(value = {
	//Get user by email
	@NamedQuery(name = "users.getByEmail", query = "FROM User u WHERE u.email = :email"),
	//Get all users
	@NamedQuery(name = "users.getAll", query = "FROM User"),
	//Get user from email/password pair
	@NamedQuery(name = "users.getIfValid", query = "FROM User u WHERE u.email = :email AND u.password = :password")
})
public class UserUtil {

	public static String getPasswordHash(String password) {
		return new MD5Encoder().encode(password.getBytes());
	}
	
	/**
	 * Gets a single user by email
	 * @param email the email belonging to the user
	 * @return user object if email is valid, null otherwise
	 */
	public static User getByEmail(String email) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		Query q = s.getNamedQuery("users.getByEmail");
		q.setParameter("email", email);
		User res = null;
		try {
			res = (User)q.uniqueResult();
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
		}
		return res;
	}

	/**
	 * Gets all users registered with the system
	 * @return collection of all users
	 */
	@SuppressWarnings("unchecked")
	public static List<User> getAll() {
		Query q = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("users.getAll");
		return (List<User>)q.list();
	}
	
	/**
	 * Gets a single user by email and password hash
	 * @param email the email belonging to the user
	 * @param pw_hash the password hash to try as password
	 * @return user object if email/pw-hash pair is valid, null otherwise
	 */
	public static User getIfValid(String email, String pw_hash) {
		Query q = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("users.getIfValid");
		q.setParameter("email", email);
		q.setParameter("password", pw_hash);
		User res = null;
		try {
			res = (User)q.uniqueResult();
		} catch (HibernateException e) {}
		return res;
	}
}
