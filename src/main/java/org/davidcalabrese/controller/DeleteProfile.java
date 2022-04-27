package org.davidcalabrese.controller;

import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;
import org.davidcalabrese.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Deletes a user's profile
 */
@WebServlet(name = "DeleteProfile", urlPatterns = { "/delete_profile/*" })
public class DeleteProfile extends HttpServlet  {
  /**
   * Called by server to allow servlet to handle a POST request
   *
   * @param req               object containing req client has made of the servlet
   * @param resp              object that containing resp servlet sends to the client
   * @throws ServletException if an input or output error is detected when handling GET req
   * @throws IOException      if the request for the GET could not be handled
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int userId = Util.getId(req.getPathInfo());
    GenericDao<User> userDao = new GenericDao<>(User.class);

    User userToDelete = userDao.getById(userId);
    userDao.delete(userToDelete); // delete user in db
    req.getSession().invalidate();  // end session

    String url = "/jsp/profile_deleted.jsp";  // forward to confirmation pg
    getServletContext().getRequestDispatcher(url).forward(req, resp);
  }
}
