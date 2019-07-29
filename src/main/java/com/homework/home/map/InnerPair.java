package com.homework.home.map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class InnerPair<K, V> {
    private K key;
    private V value;

    private InnerPair nextElement;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InnerPair<?, ?> innerPair = (InnerPair<?, ?>) o;
        return key.equals(innerPair.key) &&
                value.equals(innerPair.value);
    }

    public InnerPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public InnerPair getNextElement() {
        return nextElement;
    }

    public void setNextElement(InnerPair nextElement) {
        this.nextElement = nextElement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
