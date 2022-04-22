package org.davidcalabrese.persistence;

import org.davidcalabrese.entity.OpenAIResponse;
import org.davidcalabrese.services.OpenAIService;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenAITest {
//    @Test
//    public void shouldGetGenericBlogPost() {
//        OpenAIService service = new OpenAIService();
//
//        Response resp = service.getGenericBlogPost();
//        ObjectMapper mapper = new ObjectMapper();
//        OpenAIResponse openAIResponse = null;
//
//        assertEquals(200, resp.code());
//
//        try {
//            openAIResponse = mapper.readValue(Objects.requireNonNull(resp.body()).string(),
//                    OpenAIResponse.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        assert openAIResponse != null;
//        assertEquals("length", openAIResponse.getChoices().get(0).getFinishReason());
//    }

    @Test
    public void shouldBuildPromptFromTopicList() {
        OpenAIService service = new OpenAIService();

        List<String> topics = new ArrayList<>(Arrays.asList("sports", "entertainment", "technology"));
        String topicString = service.buildPromptFromTopicList(topics);
        assertEquals("Write a blog post about sports and entertainment and technology", topicString);

        List<String> singleTopic = new ArrayList<>(List.of("sports"));
        String singletTopicString = service.buildPromptFromTopicList(singleTopic);
        assertEquals("Write a blog post about sports", singletTopicString);
    }

    @Test
    public void shouldGetPostOnTopic() {
        OpenAIService service = new OpenAIService();
        List<String> topic = new ArrayList<>(List.of("sports"));

        OpenAIResponse resp = null;
        try {
            resp = service.getPostOnTopic(topic);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("?", resp.getChoices().get(0).getText());
    }
}
