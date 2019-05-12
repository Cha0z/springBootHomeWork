package com.homework.home.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor

public class InnerPair<K, V> {
    private K key;
    private V value;
    private List<InnerPair<K, V>> elements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InnerPair<?, ?> innerPair = (InnerPair<?, ?>) o;
        return key.equals(innerPair.key) &&
                value.equals(innerPair.value) &&
                Objects.equals(elements, innerPair.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, elements);
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

    public List<InnerPair<K, V>> getElements() {
        return elements;
    }

    public void setElements(List<InnerPair<K, V>> elements) {
        this.elements = elements;
    }
}
