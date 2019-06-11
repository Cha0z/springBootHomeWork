package com.homework.home.models;


import java.util.Objects;

public class Shop {


    private Long id;

    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id.equals(shop.id) &&
                name.equals(shop.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
