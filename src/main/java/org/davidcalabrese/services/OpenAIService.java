package org.davidcalabrese.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.entity.OpenAIResponse;
import org.davidcalabrese.util.JsonBodyHandler;
import org.davidcalabrese.util.PropertiesLoader;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Makes a post request to the OpenAI API to generate a blog post
 */
@Path("/open_ai")
public class OpenAIService implements PropertiesLoader {
    Properties properties;
    public static String API_KEY;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String BASE_URL = "https://api.openai.com/v1/engines/text-davinci-002/completions";

    /**
     * Makes the POST request to the API
     *
     * @param topics a list of topics the blog post should be about
     * @return API response mapped to the OpenAIResponse entity
     * @throws IOException  for io errors
     * @throws InterruptedException  for when thread is interrupted
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public OpenAIResponse getPostOnTopic(@FormParam("tags") List<String> topics) throws IOException, InterruptedException {
        loadProperties();
        String prompt = buildPromptFromTopicList(topics);

        var values = new HashMap<String, Object>() {{
            put("prompt", prompt);
            put("max_tokens", 600);
            put("temperature", 1);
            put("stop", "\"\"\"");
        }};

        var objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(values);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(BASE_URL))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", API_KEY)
                .build();

        return HttpClient.newHttpClient()
                        .send(request, new JsonBodyHandler<>(OpenAIResponse.class))
                        .body();
    }


    /**
     * Takes a list of topic(s) and formulates a string prompt to be sent to the OpenAI API.
     * <p>
     * If the select list is empty it will send back the prompt <pre>Write a blog post</pre>
     * <p>
     * If one or more items from select dropdown are selected they are concatenated into a <code>StringJoiner</code>
     * which will become the prompt sent to the OpenAI API. For example, if the user chooses "sports",
     * "politics" and "entertainment" from the select dropdown, <code>buildPromptFromTopicList</code> will create this prompt:
     *
     * <pre>
     *   Write a blog post about sports and politics and entertainment.
     * </pre>
     *
     *
     * @param topics the list of topics
     * @return the prompt to be sent to the API
     */
    public String buildPromptFromTopicList(List<String> topics) {
        StringJoiner sj = new StringJoiner(" and ", "about", ".");
        for (String topic : topics) {
            sj.add(topic);
        }
        return "Write a blog post " + sj;
    }

    /**
     * Loads the api key into the API_KEY field
     */
    private void loadProperties() {
        try {
            properties = loadProperties("/api.properties");
            API_KEY = properties.getProperty("openAIKey");
        } catch (IOException ioException) {
            logger.error("Cannot load properties..." + ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading properties" + e.getMessage(), e);
        }
    }
}
