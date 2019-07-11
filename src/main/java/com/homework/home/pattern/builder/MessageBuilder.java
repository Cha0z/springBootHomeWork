package com.homework.home.pattern.builder;

import com.homework.home.models.Message;

import java.time.ZonedDateTime;

public class MessageBuilder {

    private Message message;

      public MessageBuilder() {
        message = new Message();
    }

    public MessageBuilder from(String from) {
        message.setFrom(from);
        return this;
    }

    public MessageBuilder to(String to) {
        message.setTo(to);
        return this;
    }

    public MessageBuilder content(String content) {
        message.setContent(content);
        return this;
    }

    public MessageBuilder dateTime(ZonedDateTime dateTime) {
        message.setDateTime(dateTime);
        return this;
    }


    public static MessageBuilder builder(){return new MessageBuilder();}

    public Message build() {
        return message;
    }


}
