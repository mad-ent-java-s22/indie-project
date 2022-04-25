package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.Tag;
import org.davidcalabrese.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.davidcalabrese.util.Util.getId;

/**
 *  Contains method for displaying all posts with tagId = to * placeholder in urlPattern
 */
@WebServlet(name = "DisplayPostsByTag", urlPatterns = { "/tags/*" })
public class DisplayPostsByTag extends HttpServlet {
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
        int tagId = getId(req.getPathInfo());    // get tag id from url
        Tag searchedTag = tagDao.getById(tagId); // get tag with id

        Set<Post> postsWithSearchedTag = searchedTag.getPosts();
        // convert from set to list to sort
        List<Post> postList = new java.util.ArrayList<>(List.copyOf(postsWithSearchedTag));
        // sort list reverse chronologically (newest first)
        postList.sort(Comparator.comparing(Post::getDateCreated).reversed());

        req.setAttribute("tag", searchedTag);
        req.setAttribute("posts", postList);
        String url = "/jsp/posts_by_tag.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
