package com.homework.home.service;

import com.homework.home.models.Shop;
import com.homework.home.models.Water;
import com.homework.home.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public void save(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public void delete(Shop shop) {
        shopRepository.delete(shop);
    }

    public List<Shop> getShopsWithName(String name) {
        return shopRepository.findByNameContaining(name);
    }

    public List<Shop> getAllShopWithNameAndWithManufacturer(String name, String manufacturer) {
       return shopRepository.findByNameContainingAndWaterManufacturer(name, manufacturer);
    }

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }


}
