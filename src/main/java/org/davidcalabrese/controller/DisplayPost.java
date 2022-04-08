package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisplayPost", urlPatterns = { "/posts/*" })
public class DisplayPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        String pathInfo = req.getPathInfo();
        // grab everything after slash following "posts" in url, should be the post id
        int postId = Integer.parseInt(pathInfo.substring(1));

        log("post id: " + postId);
        Post post = postDao.getById(postId);

        User user = (User) req.getSession().getAttribute("user");

        req.setAttribute("user", user);
        req.setAttribute("userId", user.getId());
        req.setAttribute("post", post);
        String url = "/jsp/post.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
