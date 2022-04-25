package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.davidcalabrese.util.Util.getId;

/**
 *  Contains method for displaying the user with id = * placeholder
 */
@WebServlet(name = "DisplayUser", urlPatterns = { "/users/*" })
public class DisplayUser extends HttpServlet {
    /**
     * Called by server to allow servlet to handle a GET request
     *
     * @param req               object containing req client has made of the servlet
     * @param resp              object that containing resp servlet sends to the client
     * @throws ServletException if an input or output error is detected when handling GET req
     * @throws IOException      if the request for the GET could not be handled
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        int userId = getId(req.getPathInfo());     // get user id from url path

        User user = userDao.getById(userId);       // get user
        Set<Post> usersPostsSet = user.getPosts(); // get user's posts
        // convert set to list in order to sort
        List<Post> usersPostsList = new java.util.ArrayList<>(List.copyOf(usersPostsSet));
        // sort list reverse chronologically (newest posts first)
        usersPostsList.sort(Comparator.comparing(Post::getDateCreated).reversed());
        req.setAttribute("posts", usersPostsList);
        req.setAttribute("user", user);
        String url = "/jsp/user.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
