package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Comment;
import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;
import org.davidcalabrese.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

/**
 *  Contains method for creating a new comment
 */
@WebServlet(name = "CreateComment", urlPatterns = { "/create_comment" })
public class CreateComment extends HttpServlet {
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
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("userName");
        User user = Util.getUser(userName);

        GenericDao<Comment> commentDao = new GenericDao<>(Comment.class);
        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        String commentedPostId = req.getParameter("post_id");
        Post commentedPost = postDao.getById(Integer.parseInt(commentedPostId));

        Comment newComment = new Comment(req.getParameter("content"),
                LocalDate.now(), user, commentedPost);

        req.setAttribute("postId", commentedPostId);
        String url = "/jsp/comment_added.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
