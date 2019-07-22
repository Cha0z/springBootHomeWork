package com.homework.home;

import com.homework.home.pattern.factory.MessageFactory;
import com.homework.home.pattern.factory.model.InfoMessage;
import com.homework.home.pattern.factory.model.MessageType;
import com.homework.home.pattern.factory.model.UserMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MessageFactoryTest {

  private MessageFactory messageFactory;

  @Before
  public void init() {
    messageFactory = new MessageFactory();
  }

  @Test
  public void getMessage() {
    UserMessage userMessage = messageFactory.getMessage(MessageType.INFO);
    Assert.assertTrue(userMessage instanceof InfoMessage);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getException() {
   messageFactory.getMessage(MessageType.FOR_TEST);
  }

}
