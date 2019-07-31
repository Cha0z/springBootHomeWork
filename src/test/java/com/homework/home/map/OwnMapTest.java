package com.homework.home;

import static org.junit.Assert.assertEquals;

import com.homework.home.map.OwnMap;
import org.junit.Before;
import org.junit.Test;

public class OwnMapTest {

    private OwnMap<Integer, String> ownMap;

    private Integer testKey = 1;
    private String testValue = "test";
    private String testValueForSameHashCode = "same";


    @Before
    public void init() {
        ownMap = new OwnMap<>();
    }

    @Test
    public void addingElementToEmptyBucketTest() {
        ownMap.put(testKey, testValue);
        ownMap.put(testKey + 1, "asd");
        ownMap.put(testKey + 2, "qwe");
        ownMap.put(testKey + 3, "azx");

        assertEquals(testValue, ownMap.get(testKey));
        assertEquals(String.valueOf(4), ownMap.getSize().toString());
    }

    @Test
    public void addingElementToBucketWithSameHashCode() {
        ownMap.put(testKey, testValue);
        ownMap.put(testKey, testValueForSameHashCode);
        assertEquals(testValueForSameHashCode, ownMap.get(testKey));
    }
}
