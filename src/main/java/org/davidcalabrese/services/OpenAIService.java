package org.davidcalabrese.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.davidcalabrese.util.PropertiesLoader;


import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/open_ai")
public class OpenAIService implements PropertiesLoader {
    Properties properties;
    public static String API_KEY;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String BASE_URL = "https://api.openai.com/v1/engines/text-davinci-002/completions";

    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json")
    public Response getGenericBlogPost() {
        // Returns a generic blog post
        loadProperties();

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .build();

        RequestBody body = RequestBody.create("{ " +
                "\"prompt\": \"Write a blog post\",  " +
                "\"max_tokens\": 5,  " +
                "\"temperature\": 0.9,  " +
                "\"top_p\": 1,  " +
                "\"n\": 1,  " +
                "\"stream\": false,  " +
                "\"logprobs\": null,  " +
                "\"stop\": [\"\\\"\\\"\\\"\"]}", okhttp3.MediaType.parse(APPLICATION_JSON));
        Request request = new Request.Builder()
                .url(BASE_URL)
                .method("POST", body)
                .addHeader("Authorization", API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();
        System.out.println(request);
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json")
    public Response getPostOnTopic(List<String> topics) {
        loadProperties();

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .build();

        String topicString = getTopicStringFromList(topics);

        String prompt = "Write a blog post about " + topicString;

        RequestBody body = RequestBody.create("{ " +
                "\"prompt\": \"" + prompt + "\",  " +
                "\"max_tokens\": 200,  " +
                "\"temperature\": 0.9,  " +
                "\"top_p\": 1,  " +
                "\"n\": 1,  " +
                "\"stream\": false,  " +
                "\"logprobs\": null,  " +
                "\"stop\": [\"\\\"\\\"\\\"\"]}", okhttp3.MediaType.parse(APPLICATION_JSON));
        Request request = new Request.Builder()
                .url(BASE_URL)
                .method("POST", body)
                .addHeader("Authorization", API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();
        System.out.println(request);
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getTopicStringFromList(List<String> topics) {
        StringJoiner sj = new StringJoiner(" and ");
        for (String topic : topics) {
            sj.add(topic);
        }
        return sj.toString();
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
