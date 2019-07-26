package com.homework.home.map;

import static org.junit.Assert.assertEquals;

import com.homework.home.map.OwnMap;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

public class OwnMapTest {

  private OwnMap ownMap;

  private Integer testKey = 1;
  private String testValue = "test";


  @Before
  public void init() {
    ownMap = new OwnMap();
  }

  @Test
  public void addingTest() {
    ownMap.put(testKey, testValue);
    assertEquals(testValue, ownMap.get(testKey));
  }



  private void addElementsToMap(Integer index) {
    Random random = new Random();
    while (index != 0) {
      ownMap.put(random.nextInt(20), testValue);
      index--;
    }
  }

}
