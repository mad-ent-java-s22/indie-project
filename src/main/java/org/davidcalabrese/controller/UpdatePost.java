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
import java.io.IOException;
import java.util.Set;

/**
 *  Contains method for updating post with id = * placeholder
 */
@WebServlet(name = "UpdatePost", urlPatterns = { "/update_post/*" })
public class UpdatePost extends HttpServlet {
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
        int postId = Util.getId(req.getPathInfo());
        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        Post postToUpdate = postDao.getById(postId); // fetch post with id

        // get updated form fields from
        postToUpdate.setTitle(req.getParameter("title"));
        postToUpdate.setSummary(req.getParameter("summary"));
        postToUpdate.setContent(req.getParameter("content"));

        // store values from tag <select> as an array
        String[] tagArray = req.getParameterValues("tags");
        Set<Tag> tagSet = Util.makeTagSet(tagArray);
        postToUpdate.setTags(tagSet);

        postDao.saveOrUpdate(postToUpdate);

        req.setAttribute("post", postToUpdate);
        String url = "/jsp/post_updated.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
