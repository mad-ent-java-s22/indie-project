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

@WebServlet(name = "DisplayAllPosts", urlPatterns = { "/all_posts" })
public class DisplayAllPosts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/jsp/all_posts.jsp";
        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        // get all posts, reverse the list so newest are displayed first
        List<Post> posts = postDao.getAll();
        Collections.reverse(posts);

        req.setAttribute("posts", posts);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
