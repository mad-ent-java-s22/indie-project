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

@WebServlet(name = "CreateComment", urlPatterns = { "/create_comment" })
public class CreateComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processComment(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processComment(req, resp);
    }

    private void processComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("userName");
        User user = Util.getUser(userName);

        GenericDao<Comment> commentDao = new GenericDao<>(Comment.class);
        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        String commentedPostId = req.getParameter("post_id");
        Post commentedPost = postDao.getById(Integer.parseInt(commentedPostId));

        Comment newComment = new Comment(req.getParameter("content"),
                LocalDate.now(), user, commentedPost);

        commentDao.insert(newComment);
        log("Inserting comment: " + newComment);

        String url = "/posts/" + commentedPostId;
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}