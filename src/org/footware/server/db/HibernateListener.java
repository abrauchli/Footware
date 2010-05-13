package org.footware.server.db;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
    	// call the static initializer of that class
        HibernateUtil.getSessionFactory();    
    }
 
    public void contextDestroyed(ServletContextEvent event) {
    	// Free all resources
    	HibernateUtil.getSessionFactory().close();
    }
}
