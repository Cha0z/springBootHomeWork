package com.homework.home.pattern.factory;

import com.homework.home.pattern.factory.model.InfoMessage;
import com.homework.home.pattern.factory.model.MessageType;
import com.homework.home.pattern.factory.model.UserMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MessageFactoryTest {

  private MessageFactory messageFactory;

  @Before
  public void init() {
    messageFactory = new MessageFactory();
  }

  @Test
  public void getMessage() {
    UserMessage userMessage = messageFactory.getMessage(MessageType.INFO);
    assertTrue(userMessage instanceof InfoMessage);
  }

  @Test
  public void testAllMessageTypes() {
    for(MessageType type:MessageType.values()){
      assertNotNull(messageFactory.getMessage(type));
    }

  }

}
