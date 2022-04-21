package org.davidcalabrese.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import org.davidcalabrese.entity.OpenAIResponse;
import org.davidcalabrese.services.OpenAIService;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenAITest {
    @Test
    public void shouldGetGenericBlogPost() {
        OpenAIService service = new OpenAIService();

        Response resp = service.getGenericBlogPost();
        ObjectMapper mapper = new ObjectMapper();
        OpenAIResponse openAIResponse = null;

        assertEquals(200, resp.code());

        try {
            openAIResponse = mapper.readValue(Objects.requireNonNull(resp.body()).string(),
                    OpenAIResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert openAIResponse != null;
        assertEquals("length", openAIResponse.getChoices().get(0).getFinishReason());
    }

    @Test
    public void shouldGetTopicStringFromList() {
        OpenAIService service = new OpenAIService();

        List<String> topics = new ArrayList<>(Arrays.asList("sports", "entertainment", "technology"));
        String topicString = service.getTopicStringFromList(topics);
        assertEquals("sports and entertainment and technology", topicString);

        List<String> singleTopic = new ArrayList<>(List.of("sports"));
        String singletTopicString = service.getTopicStringFromList(singleTopic);
        assertEquals("sports", singletTopicString);
    }

    @Test
    public void shouldGetPostOnTopic() {
        OpenAIService service = new OpenAIService();
        List<String> topic = new ArrayList<>(List.of("sports"));

        Response resp = service.getPostOnTopic(topic);
        ObjectMapper mapper = new ObjectMapper();
        OpenAIResponse openAIResponse = null;

        assertEquals(200, resp.code());

        try {
            openAIResponse = mapper.readValue(Objects.requireNonNull(resp.body()).string(),
                    OpenAIResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert openAIResponse != null;
        assertEquals("text-davinci:002", openAIResponse.getModel());
    }
}
