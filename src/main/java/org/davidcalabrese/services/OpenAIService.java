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

@Path("/open_ai")
public class OpenAIService implements PropertiesLoader {
    Properties properties;
    public static String API_KEY;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String BASE_URL = "https://api.openai.com/v1/engines/text-davinci-002/completions";

//    @POST
//    // The Java method will produce content identified by the MIME Media type "text/plain"
//    @Produces("application/json")
//    public Response getGenericBlogPost() {
//        // Returns a generic blog post
//        loadProperties();
//
//        OkHttpClient client = new OkHttpClient()
//                .newBuilder()
//                .build();
//
//        RequestBody body = RequestBody.create("{ " +
//                "\"prompt\": \"Write a blog post\",  " +
//                "\"max_tokens\": 5,  " +
//                "\"temperature\": 0.9,  " +
//                "\"top_p\": 1,  " +
//                "\"n\": 1,  " +
//                "\"stream\": false,  " +
//                "\"logprobs\": null,  " +
//                "\"stop\": [\"\\\"\\\"\\\"\"]}", okhttp3.MediaType.parse(APPLICATION_JSON));
//        Request request = new Request.Builder()
//                .url(BASE_URL)
//                .method("POST", body)
//                .addHeader("Authorization", API_KEY)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        System.out.println(request);
//        try {
//            return client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public OpenAIResponse getPostOnTopic(@FormParam("tags") List<String> topics) throws IOException, InterruptedException {
        loadProperties();
        logger.info("Key: " + API_KEY);
        String prompt = buildPromptFromTopicList(topics);

        var values = new HashMap<String, Object>() {{
            put("prompt", prompt);
            put("max_tokens", 50);
            put("temperature", 0.9);
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



    public String buildPromptFromTopicList(List<String> topics) {
        StringJoiner sj = new StringJoiner(" and ");
        for (String topic : topics) {
            sj.add(topic);
        }
        return "Write a blog post about " + sj;
    }

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
