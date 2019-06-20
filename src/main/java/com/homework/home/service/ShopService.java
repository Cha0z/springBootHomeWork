package com.homework.home.service;

import com.homework.home.models.Shop;

import java.util.List;

public interface ShopService {


    void save(Shop shop);

    void delete(Shop shop);

    List<Shop> getShopsWithName(String name);

//    List<Shop> getAllShopWithNameAndWithManufacturer(String name, String manufacturer);
}
