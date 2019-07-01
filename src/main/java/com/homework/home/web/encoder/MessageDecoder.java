package com.homework.home.web.encoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.home.models.Message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

public class MessageDecoder implements Decoder.Text<Message> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Message decode(String s) throws DecodeException {
        try {
            return objectMapper.readValue(s, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
