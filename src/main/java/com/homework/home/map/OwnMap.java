package com.homework.home.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OwnMap<K, V> {

    private List<InnerPair> elements;


    public OwnMap() {
        elements = new ArrayList<>(16);
    }

    public Integer getSize() {
        Integer size = 0;

        for (InnerPair pair : elements) {
            if (pair != null) {
                size++;
            }
        }
        return size;
    }

    public V put(K key, V value) {
        int numberOfBucket = numberOfBucket(key);
        V oldValue = null;
        if (elements.get(numberOfBucket) == null) {
            InnerPair innerPair = getInnerPair(key, value);
            elements.add(numberOfBucket, innerPair);
        } else {
            if (key.equals(elements.get(numberOfBucket).getKey())) {
                oldValue = (V) elements.get(numberOfBucket).getValue();
                elements.get(numberOfBucket).setValue(value);
            } else {
                InnerPair innerPair = getInnerPair(key, value);
                elements.get(numberOfBucket).setNextElement(innerPair);
            }
        }
        return oldValue;
    }

    private InnerPair getInnerPair(K key, V value) {
        InnerPair innerPair = new InnerPair();
        innerPair.setKey(key);
        innerPair.setValue(value);
        return innerPair;
    }

    private int numberOfBucket(K key) {
        return key.hashCode() % elements.size();
    }

    public V get(K key) {
        int numberOfBucket = numberOfBucket(key);
        Optional<InnerPair> checkingValue = Optional.ofNullable(elements.get(numberOfBucket));

        return checkingValue.map(innerPair -> analyzeKey(innerPair, key)).orElse(null);
    }

    private V analyzeKey(InnerPair innerPair, K key) {

        if (innerPair.getKey().equals(key)) {
            return (V) innerPair.getValue();
        } else {
            if (innerPair.getNextElement() != null) {
                return analyzeKey(innerPair, key);
            }
        }
        return null;
    }
}
