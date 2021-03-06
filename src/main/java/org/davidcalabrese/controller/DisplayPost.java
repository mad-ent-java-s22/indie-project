package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Comment;
import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *  Displays the post with id = * placeholder in URL
 */
@WebServlet(name = "DisplayPost", urlPatterns = { "/posts/*" })
public class DisplayPost extends HttpServlet {

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
        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        String pathInfo = req.getPathInfo();                  // grab post id from url
        int postId = Integer.parseInt(pathInfo.substring(1)); // convert to int
        Post post = postDao.getById(postId);                  // get post

        User user = (User) req.getSession().getAttribute("user");  // get current user

        List<Comment> comments = new ArrayList<>(List.copyOf(post.getComments()));  // get all post comments
        comments.sort(Comparator.comparing(Comment::getDateCreated).reversed());    // reverse (newest first)

        req.setAttribute("comments", comments);
        req.setAttribute("user", user);
        req.setAttribute("userId", user.getId());
        req.setAttribute("post", post);
        String url = "/jsp/post.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    /**
     * Triggered when submitting comment form to post page. Pass to doGet.
     *
     * @param req               object containing req client has made of the servlet
     * @param resp              object that containing resp servlet sends to the client
     * @throws ServletException if an input or output error is detected when handling GET req
     * @throws IOException      if the request for the GET could not be handled
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
