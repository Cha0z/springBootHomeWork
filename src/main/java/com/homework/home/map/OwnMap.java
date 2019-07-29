package com.homework.home.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OwnMap<K, V> {

    private List<InnerPair<K, V>> elements;

    private Integer counter = 0;


    public OwnMap() {
        elements = new ArrayList<>(16);

        addSizeToList();
    }

    private void addSizeToList() {
        for (int i = 1; i < 16; i++) {
            elements.add(null);
        }
    }

    public Integer getSize() {
        return counter;
    }

    private void countInnerElements(InnerPair<K, V> innerPair, Integer size) {
        if (innerPair.getNextElement() != null) {
            size++;
            countInnerElements(innerPair, size);
        }
    }


    public V put(K key, V value) {
        int numberOfBucket = numberOfBucket(key);
        log.debug("Chosen position for key: {} ", numberOfBucket);
        V oldValue = null;
        if (elements.get(numberOfBucket) == null) {
            InnerPair<K, V> innerPair = getInnerPair(key, value);
            elements.add(numberOfBucket, innerPair);
        } else {
            if (key.equals(elements.get(numberOfBucket).getKey())) {
                oldValue = elements.get(numberOfBucket).getValue();
                elements.get(numberOfBucket).setValue(value);
                counter--;
            } else {
                InnerPair<K, V> innerPair = getInnerPair(key, value);
                InnerPair lastPair = getLastInnerPair(elements.get(numberOfBucket));
                lastPair.setNextElement(innerPair);
            }
        }
        counter++;
        log.info("Element was added :{} for key {}", value, key);
        return oldValue;
    }


    private InnerPair getLastInnerPair(InnerPair innerPair) {
        if (innerPair.getNextElement() != null) {
            getLastInnerPair(innerPair.getNextElement());
        } else {
            return innerPair;
        }
        return null;
    }

    private InnerPair<K, V> getInnerPair(K key, V value) {
        return new InnerPair<>(key, value);
    }

    private int numberOfBucket(K key) {
        if (key == null) {
            return 1;
        }
        return key.hashCode() % elements.size();
    }

    public V get(K key) {

        int numberOfBucket = numberOfBucket(key);
        log.debug("Chosen position for key: {} ", numberOfBucket);
        Optional<InnerPair> checkingValue = Optional.ofNullable(elements.get(numberOfBucket));

        return checkingValue.map(innerPair -> getValueUsingKey(innerPair, key)).orElse(null);
    }

    private V getValueUsingKey(InnerPair innerPair, K key) {

        if (innerPair.getKey().equals(key)) {
            return (V) innerPair.getValue();
        } else {
            if (innerPair.getNextElement() != null) {
                return getValueUsingKey(innerPair.getNextElement(), key);
            }
        }
        return null;
    }
}
