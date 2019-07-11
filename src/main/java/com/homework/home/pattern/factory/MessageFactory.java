package com.homework.home.pattern.factory;

import com.homework.home.pattern.factory.model.AlertMessage;
import com.homework.home.pattern.factory.model.InfoMessage;
import com.homework.home.pattern.factory.model.MessageType;
import com.homework.home.pattern.factory.model.UserMessage;

public class MessageFactory {


  public UserMessage getMessage(MessageType messageType) {
    UserMessage message = null;

    switch (messageType) {
      case ALLERT:
        message = new AlertMessage();
        break;
      case INFO:
        message = new InfoMessage();
        break;
      default:
        throw new IllegalArgumentException("Illegal messageType for factory ; {}" + messageType);
    }
    return message;
  }

}
