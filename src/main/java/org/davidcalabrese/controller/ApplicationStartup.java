package org.davidcalabrese.controller;

import org.davidcalabrese.util.PropertiesLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * This class contains an init method to be run each time the application is initialized.
 */
@WebServlet(name="application_startup", urlPatterns = { "/application_startup"}, loadOnStartup = 1)
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {

}
