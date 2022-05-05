package org.davidcalabrese.controller;

import org.davidcalabrese.util.PropertiesLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

/**
 * This class contains an init method to be run each time the application is initialized.
 */
@WebServlet(name="application_startup", urlPatterns = { "/application_startup"}, loadOnStartup = 1)
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {
  @Override
  public void init() throws ServletException {
    Properties props = null;
    try {
      props = loadProperties("/cognito.properties");
    } catch (Exception e) {
      e.printStackTrace();
    }
    getServletContext().setAttribute("cognitoProps", props);
  }
}
