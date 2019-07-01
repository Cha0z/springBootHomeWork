package com.homework.home.web.encoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.home.models.Message;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

    private ObjectMapper objectMapper= new ObjectMapper();


    @Override
    public String encode(Message message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        //what should be here??
    }

    @Override
    public void destroy() {
        //what should be here??
    }
}
