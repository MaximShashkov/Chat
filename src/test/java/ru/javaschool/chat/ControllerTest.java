package ru.javaschool.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.javaschool.chat.entities.Message;
import ru.javaschool.chat.utils.MessageResponse;
import ru.javaschool.chat.utils.MessageStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


import static org.junit.Assert.*;

/**
 * Реализация интеграционного теста.
 * Для наполнения БД необходимо выполнить:
  INSERT into CHATS  VALUES (1, FALSE);
  INSERT into CHATS  VALUES (2, FALSE);
  INSERT into CHATS  VALUES (3, TRUE);
  INSERT into CHATS  VALUES (4, TRUE);
  INSERT into PEOPLE  VALUES (1, 'First');
  INSERT into PEOPLE  VALUES (2, 'Second');
  INSERT into PEOPLE  VALUES (3, 'Third');
  INSERT into MESSAGES VALUES (2,'1 message in the 2 chat',{ts '2020-01-24 22:43:52.69'},2,2);
  INSERT into MESSAGES VALUES (3,'2 message in the 2 chat',{ts '2020-01-24 22:43:52.69'},2,2);
  INSERT into MESSAGES VALUES (4,'1 message in the 3 chat',{ts '2020-01-24 22:45:58.69'},3,3);
  INSERT into MESSAGES VALUES (5,'1 message in the 4 chat',{ts '2020-01-24 22:45:58.69'},4,3);
 */
public class ControllerTest {
    private static String sendMessageURL;
    private static String getMessagesFromCurrentURL;
    private static String getMessagesFromClosedURL;
    private static RestTemplate restTemplate;
    private static HttpHeaders headers;
    private static JSONObject messageRequestObject;
    private static final long FIRSTMESSAGE = 1;
    private static final List<Long> CURRENTMESSAGEIDS = new ArrayList<>();
    private static final List<Long> CLOSEDMESSAGEIDS = new ArrayList<>();

    @BeforeClass
    public static void runBeforeAllTestMethods() throws JSONException {
        sendMessageURL = "http://localhost:8080/sendMessage";
        getMessagesFromCurrentURL = "http://localhost:8080/getMessagesFromCurrent?chatId=2";
        getMessagesFromClosedURL = "http://localhost:8080/getMessagesFromClosed";
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        messageRequestObject = new JSONObject();
        messageRequestObject.put("authorID", 1L);
        messageRequestObject.put("chatID", 1L);
        messageRequestObject.put("textMessage", "Hello Test");

        CURRENTMESSAGEIDS.add(2L);
        CURRENTMESSAGEIDS.add(3L);
        CLOSEDMESSAGEIDS.add(4L);
        CLOSEDMESSAGEIDS.add(5L);
    }

    @Test
    public void sendMessage() {
        HttpEntity<String> request = new HttpEntity<>(messageRequestObject.toString(), headers);
        MessageResponse messageResponse = restTemplate.postForObject(sendMessageURL, request, MessageResponse.class);
        assertNotNull(messageResponse);
        assertEquals(messageResponse.getStatuses().get(0), MessageStatus.SUCCESS);
        assertEquals(messageResponse.getMessageID(), FIRSTMESSAGE);
    }

    @Test
    public void getMessagesFromCurrent() {
        List<LinkedHashMap> messages = restTemplate.getForObject(getMessagesFromCurrentURL, List.class);
        List<Long> ids = idExtractor(messages);
        assertEquals(ids, CURRENTMESSAGEIDS);
    }

    @Test
    public void getMessagesFromClosed() {
        List<LinkedHashMap> messages = restTemplate.getForObject(getMessagesFromClosedURL, List.class);
        List<Long> ids = idExtractor(messages);
        assertEquals(ids, CLOSEDMESSAGEIDS);
    }

    private static List<Long> idExtractor(List<LinkedHashMap> messages) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        List<Long> ids;
        ids = messages.stream()
                .map(message -> objectMapper.convertValue(message, Message.class).getId())
                .sorted()
                .collect(Collectors.toList());
        return ids;
    }
}