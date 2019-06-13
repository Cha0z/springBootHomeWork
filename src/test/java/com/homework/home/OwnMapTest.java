package com.homework.home;

import com.homework.home.map.OwnMap;
import org.junit.Before;
import org.junit.Test;

public class OwnMapTest {

  private OwnMap ownMap;


  @Before
  public void init() {
    ownMap = new OwnMap();
  }
  @Test
  public void test() {
    ownMap.put("asd","asd");
    ownMap.put(1,"azxcsd");
    ownMap.put(2,"axz");

    ownMap.put("asd","asdgsdg");
    System.out.println(ownMap.getSize());
    System.out.println(ownMap.get(1));
  }

}
