package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.Tag;
import org.davidcalabrese.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static org.davidcalabrese.util.Util.getId;

@WebServlet(name = "DisplayPostsByTag", urlPatterns = { "/tags/*" })
public class DisplayPostsByTag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<Post> postDao = new GenericDao<>(Post.class);
        GenericDao<Tag> tagDao = new GenericDao<>(Tag.class);

        int tagId = getId(req.getPathInfo());

        Tag searchedTag = tagDao.getById(tagId);

        Set<Post> postsWithSearchedTag = searchedTag.getPosts();

        req.setAttribute("tag", searchedTag);
        req.setAttribute("posts", postsWithSearchedTag);
        String url = "/jsp/posts_by_tag.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
