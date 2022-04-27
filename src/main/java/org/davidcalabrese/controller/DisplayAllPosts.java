package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Post;
import org.davidcalabrese.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *  Contains method for displaying all blog posts
 */
@WebServlet(name = "DisplayAllPosts", urlPatterns = { "/all_posts" })
public class DisplayAllPosts extends HttpServlet {
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
        String url = "/jsp/all_posts.jsp";
        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        // get all posts, reverse the list so newest are displayed first
        List<Post> posts = postDao.getAll();
        Collections.reverse(posts);

        List<Post> tenMostRecentPosts = posts.subList(0, 10);

        req.setAttribute("posts", tenMostRecentPosts);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
