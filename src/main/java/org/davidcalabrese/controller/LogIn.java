package org.davidcalabrese.controller;

import org.davidcalabrese.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 *  Contains methods for logging a user in with cognito.
 */
@WebServlet(urlPatterns = {"/logIn"})
/* Begins the authentication process using AWS Cognito */
public class LogIn extends HttpServlet implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String LOGIN_URL;
    public static String REDIRECT_URL;

    @Override
    public void init() throws ServletException {
        super.init();
        getPropsFromServletContext();
    }

    /**
     * Fetches all the cognito properties that were stored in the application context in the ApplicationStartup class.
     */
    private void getPropsFromServletContext() {
        Properties cognitoProps = (Properties) getServletContext().getAttribute("cognitoProps");
        CLIENT_ID = cognitoProps.getProperty("client.id");
        LOGIN_URL = cognitoProps.getProperty("loginURL");
        REDIRECT_URL = cognitoProps.getProperty("redirectURL");

    }

    /**
     * Route to the aws-hosted cognito login page.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException general servlet issues
     * @throws IOException exceptions involving IO
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String url = LOGIN_URL + "?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL;
            resp.sendRedirect(url);
        } catch (Exception e) {
            logger.error("Error loading cognito properties " + e.getMessage(), e);
            resp.sendRedirect("/error");
        }
    }
}
