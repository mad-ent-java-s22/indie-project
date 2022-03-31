package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Post;
import org.davidcalabrese.persistence.GenericDao;
import org.davidcalabrese.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdatePost", urlPatterns = { "/update_post/*" })
public class UpdatePost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Util.getId(req.getPathInfo());
        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        Post postToUpdate = postDao.getById(postId); // fetch post with id

        // get updated form fields from
        postToUpdate.setTitle(req.getParameter("title"));
        postToUpdate.setSummary(req.getParameter("summary"));
        postToUpdate.setContent(req.getParameter("content"));
        postDao.saveOrUpdate(postToUpdate);

        req.setAttribute("post", postToUpdate);

        String url = "/jsp/post_updated.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
