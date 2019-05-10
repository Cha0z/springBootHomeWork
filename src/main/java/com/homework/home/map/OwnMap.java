package com.homework.home.map;

import java.util.ArrayList;
import java.util.List;

public class OwnMap {

    private List<InnerPair> elements;


    public OwnMap() {
        elements = new ArrayList<>();
    }

    public Integer getSize() {
        return elements.size();
    }

    public void put(Object key, Object value) {
        if (elements.stream().anyMatch(elements -> elements.equals(key))) {
            elements.add(new InnerPair(key, value));
        } else {
            elements.stream().filter(elements -> elements.equals(key)).findFirst().get().setValue(value);
        }
    }

    public Object get(Object key) {
        return elements.stream().filter(element -> element.equals(key))
                .findFirst().orElse(null);
    }

}
