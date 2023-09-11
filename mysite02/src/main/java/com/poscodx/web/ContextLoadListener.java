package com.poscodx.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {

	/* Application이 올라올 때 */
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		String contextConfigLocation = sc.getInitParameter("contextConfigLocation");
		System.out.println("Application starts....." + contextConfigLocation);
	}

	/* Application이 내려갈 때 */
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
