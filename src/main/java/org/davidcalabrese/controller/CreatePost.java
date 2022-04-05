package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.Tag;
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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "CreatePost", urlPatterns = { "/create_post" })
public class CreatePost extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get userName and email that from cognito, stored in session
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("userName");
        User user = Util.getUser(userName);
        log("userName: " + userName);
        log("user: " + user.toString());

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
