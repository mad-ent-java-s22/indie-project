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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.davidcalabrese.util.Util.getId;

@WebServlet(name = "DisplayPostsByTag", urlPatterns = { "/tags/*" })
public class DisplayPostsByTag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<Post> postDao = new GenericDao<>(Post.class);

        int tagId = getId(req.getPathInfo());

        List<Post> allPosts = postDao.getAll();

        List<Post> postsWithCorrectTag = getPostsByTag(allPosts, tagId);
        req.setAttribute("posts", postsWithCorrectTag);
        String url = "/jsp/postsByTag.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

    public List<Post> getPostsByTag(List<Post> posts, int tagId) {
        return posts.stream()
                .filter(post -> post.getTags().getId().contains(tagId))
                .collect(Collectors.toList());
        // TODO: need to get the id for each tag in set
        // can I do this in a stream? How to do in SQL?
    }
}
