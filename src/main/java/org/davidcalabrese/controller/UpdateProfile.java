package org.davidcalabrese.controller;

import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;
import org.davidcalabrese.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.davidcalabrese.util.Util.getUser;

@WebServlet(name = "UpdateProfile", urlPatterns = { "/update_profile" })
public class UpdateProfile extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get userName from session
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("userName");

        // fetch user from db
        User user = getUser(userName);
        GenericDao<User> userDao = new GenericDao<>(User.class);

        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
        user.setSummary(req.getParameter("about"));
        user.setProfileImage("default_profile_pic.jpg");

        // update user in db
        userDao.saveOrUpdate(user);

        // update user in session
        session.setAttribute("user", user);

        String url = "/jsp/profile_updated.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
