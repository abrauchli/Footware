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

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Setup class for hibernate
 * Registered as servlet in the appserver (web.xml)
 */
public class HibernateListener implements ServletContextListener {

	/**
	 * Initialization method for the hibernate factory
	 */
    public void contextInitialized(ServletContextEvent event) {
    	// call the static initializer of that class
        HibernateUtil.getSessionFactory();    
    }

	/**
	 * Destruction method for the hibernate factory
	 */
    public void contextDestroyed(ServletContextEvent event) {
    	// Free all resources
    	HibernateUtil.getSessionFactory().close();
    }
}
