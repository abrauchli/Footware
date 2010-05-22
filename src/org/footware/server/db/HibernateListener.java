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
