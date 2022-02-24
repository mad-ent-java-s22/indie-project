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

@WebServlet(name = "DisplayPost", urlPatterns = { "/posts/*" })
public class DisplayPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        String url = "/jsp/post/.jsp";
        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        Post post = postDao.getById(2);

        req.setAttribute("post", post);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
