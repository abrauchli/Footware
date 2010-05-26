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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Helper class for hibernate
 * Provides a session factory and methods to create independant sessions.
 */
public class HibernateUtil {

	/** The single instance of hibernate SessionFactory */
	private static SessionFactory sessionFactory;

	/**
	 * Private constructor to guaranty a single instance
	 */
	private HibernateUtil() {
	}

	/**
	 * Class initializer setting the session factory
	 */
	static {
		sessionFactory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
	}

	/**
	 * Gets the hibernate session factory
	 * @return the hibernate session factory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Opens a session and will not bind it to a session context
	 * 
	 * @return the session
	 */
	public Session openSession() {
		return sessionFactory.openSession();
	}

	/**
	 * Returns a session from the session context. If there is no session in the
	 * context it opens a session, stores it in the context and returns it. This
	 * factory is intended to be used with a hibernate.cfg.xml including the
	 * following property <property
	 * name="current_session_context_class">thread</property> This would return
	 * the current open session or if this does not exist, will create a new
	 * session
	 * 
	 * @return the session
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * closes the session factory
	 */
	public static void close() {
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;
	}
}
