package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Post;
import org.davidcalabrese.persistence.GenericDao;
import org.davidcalabrese.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Contains method for deleting post with id = * placeholder in urlPattern
 */
@WebServlet(name = "DeletePost", urlPatterns = { "/delete_post/*" })
public class DeletePost extends HttpServlet {
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
        // grab post id param from url, create post dao
        int postId = Util.getId(req.getPathInfo());
        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        Post postToDelete = postDao.getById(postId); // fetch post with id
        postDao.delete(postToDelete); // delete post

        String url = "/jsp/post_deleted.jsp";
        req.getRequestDispatcher(url).forward(req, resp);
    }
}
