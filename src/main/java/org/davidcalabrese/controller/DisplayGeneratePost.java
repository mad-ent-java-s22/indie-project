package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Tag;
import org.davidcalabrese.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *  Contains method for displaying a page where user can choose topic and generate post
 */
@WebServlet(name = "DisplayGeneratePost", urlPatterns = { "/display_generate_post" })
public class DisplayGeneratePost extends HttpServlet {
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
        GenericDao<Tag> tagDao = new GenericDao<>(Tag.class);
        List<Tag> tagNames  = tagDao.getAll();

        req.setAttribute("tagNames", tagNames);
        String url = "/jsp/generate_post.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
