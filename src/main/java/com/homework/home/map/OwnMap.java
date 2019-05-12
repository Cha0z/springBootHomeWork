package com.homework.home.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class OwnMap<V, K> {

    private List<InnerPair> elements;


    public OwnMap() {
        elements = new ArrayList<>();
    }

    public Integer getSize() {
        return elements.size();
    }

    public V put(K key, V value) {
        int numberOfBucket = numberOfBucket(key);
        V oldValue = null;
        if (!Optional.ofNullable(elements.get(numberOfBucket)).isPresent()) {
            InnerPair innerPair = getInnerPair(key, value);
            elements.add(numberOfBucket, innerPair);
        } else {
            if (key.equals(elements.get(numberOfBucket).getKey())) {
                oldValue = (V) elements.get(numberOfBucket).getValue();
                elements.get(numberOfBucket).setValue(value);

            } else {
                elements.get(numberOfBucket).setKey(null);
                List<InnerPair> innerPairList;
                if (elements.get(numberOfBucket).getElements() == null) {
                    innerPairList = new ArrayList<>();
                    innerPairList.add(elements.get(numberOfBucket));
                    InnerPair innerPair = getInnerPair(key, value);
                    innerPairList.add(innerPair);
                    elements.get(numberOfBucket).setElements(innerPairList);
                } else {
                    InnerPair innerPair = getInnerPair(key, value);
                    elements.get(numberOfBucket).getElements().add(innerPair);
                }
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
        V value = null;
        if (checkingValue.isPresent()) {
            InnerPair<K, V> innerPair = checkingValue.get();
            if (innerPair.getKey().equals(key)) {
                return innerPair.getValue();
            } else {
                List<InnerPair<K, V>> innerPairList = innerPair.getElements();
                for (InnerPair<K, V> current : innerPairList) {
                    if (current.getKey().equals(key)) {
                        return current.getValue();
                    }
                }
            }


        }
        return value;
    }

}
