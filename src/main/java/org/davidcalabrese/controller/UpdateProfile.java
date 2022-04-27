package org.davidcalabrese.controller;

import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Contains method for processing edit profile page
 */
@WebServlet(name = "UpdateProfile", urlPatterns = { "/update_profile" })
public class UpdateProfile extends HttpServlet  {
    /**
     * Called by server to allow servlet to handle a POST request
     *
     * @param req               object containing req client has made of the servlet
     * @param resp              object that containing resp servlet sends to the client
     * @throws ServletException if an input or output error is detected when handling GET req
     * @throws IOException      if the request for the GET could not be handled
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user"); // get user from session
        GenericDao<User> userDao = new GenericDao<>(User.class); // create userDao, fetch user from db

        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
        user.setSummary(req.getParameter("about"));
        user.setProfileImage(req.getParameter("profile_image"));

        userDao.saveOrUpdate(user); // update user in db

        String url = "/jsp/profile_updated.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
