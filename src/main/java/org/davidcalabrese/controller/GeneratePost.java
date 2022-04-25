package org.davidcalabrese.controller;

import org.davidcalabrese.entity.OpenAIResponse;
import org.davidcalabrese.entity.Tag;
import org.davidcalabrese.persistence.GenericDao;
import org.davidcalabrese.services.OpenAIService;
import org.davidcalabrese.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Calls OpenAI API to get a blog post on topic
 */
@WebServlet(name = "GeneratePost", urlPatterns = { "/generate_post" })
public class GeneratePost extends HttpServlet {
    /**
     * Called by server to allow servlet to handle a GET request
     *
     * @param req               object containing req client has made of the servlet
     * @param resp              object that containing resp servlet sends to the client
     * @throws ServletException if an input or output error is detected when handling GET req
     * @throws IOException      if the request for the GET could not be handled
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<Tag> tagDao = new GenericDao<>(Tag.class);
        String[] tagArray = req.getParameterValues("tags");

        List<String> tagStringList = new ArrayList<>(Arrays.asList(tagArray));
        List<Tag> tagList = Util.makeTagList(tagArray);
        List<Tag> possibleTags  = tagDao.getAll();

        OpenAIService service = new OpenAIService();

        OpenAIResponse response = null;
        try {
            response = service.getPostOnTopic(tagStringList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        assert response != null;
        String postText = response.getChoices().get(0).getText();

        String postTextWithParagraphBreaks = postText.replace("\\n\\n", "<p>");

        log("replaced text: " + postTextWithParagraphBreaks);

        req.setAttribute("postText", postTextWithParagraphBreaks);
        req.setAttribute("possibleTags", possibleTags);
        req.setAttribute("chosenTags", tagList);

        String url = "/jsp/confirm_generated_post.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
