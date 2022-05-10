package org.davidcalabrese.controller;

import org.davidcalabrese.entity.Comment;
import org.davidcalabrese.entity.Post;
import org.davidcalabrese.persistence.GenericDao;
import org.davidcalabrese.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Contains method for deleting post with id = * placeholder in urlPattern
 */
@WebServlet(name = "DeleteComment", urlPatterns = { "/delete_comment/*" })
public class DeleteComment extends HttpServlet {
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
    // grab comment id param from url, create comment dao and post daos
    int commentId = Util.getId(req.getPathInfo());
    GenericDao<Comment> commentDao = new GenericDao<>(Comment.class);
    GenericDao<Post> postDao = new GenericDao<>(Post.class);

    Comment commentToDelete = commentDao.getById(commentId); // fetch comment with id
    commentDao.delete(commentToDelete);                      // delete comment

    int idOfPostWithComment = commentToDelete.getPost().getId();  // get post id
    String url = "/posts/" + idOfPostWithComment;      // get url of post to forward back to

    getServletContext().getRequestDispatcher(url).forward(req, resp);
  }
}
