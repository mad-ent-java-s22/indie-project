package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.Tag;
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
import java.time.LocalDate;
import java.util.Set;

/**
 *  Contains method for creating a new post
 */
@WebServlet(name = "CreatePost", urlPatterns = { "/create_post" })
public class CreatePost extends HttpServlet  {
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
        // get userName and email that from cognito, stored in session
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("userName");

        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        Post newPost = new Post();
        newPost.setTitle(req.getParameter("title"));
        newPost.setSummary(req.getParameter("summary"));
        newPost.setContent(req.getParameter("content"));
        newPost.setUser(Util.getUser(userName));
        newPost.setDateCreated(LocalDate.now());

        // store values from tag <select> as an array
        String[] tagArray = req.getParameterValues("tags");
        Set<Tag> tagSet = Util.makeTagSet(tagArray);

        newPost.setTags(tagSet);

        //get id of newly inserted post and send user to that page to see submission
        int idOfInsertedPost = postDao.insert(newPost);
        req.setAttribute("postId", idOfInsertedPost);
        String url = "/jsp/post_added.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
