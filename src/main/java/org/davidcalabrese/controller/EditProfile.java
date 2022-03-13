package org.davidcalabrese.controller;

import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditProfile extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get userName and email that from cognito, stored in session
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("userName");
        String email = (String) session.getAttribute("email");
        GenericDao<User> userDao = new GenericDao<>(User.class);

        User newUser = new User(userName, email);
        newUser.setFirstName(req.getParameter("first_name"));
        newUser.setLastName(req.getParameter("last_name"));
        newUser.setSummary(req.getParameter("about"));

        // insert user and retrieve from db
        int id = userDao.insert(newUser);
        User insertedUser = userDao.getById(id);

        // save user in session
        session.setAttribute("user", insertedUser);

        String url = "/jsp/all_posts.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
