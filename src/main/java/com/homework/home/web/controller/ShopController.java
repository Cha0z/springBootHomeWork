package com.homework.home.web.controller;

import com.homework.home.models.Shop;
import com.homework.home.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopController {


    @Autowired
    private ShopService shopService;

    @PutMapping("/add")
    public void addShop(
            @RequestBody Shop shop) {
        shopService.save(shop);
    }

    @DeleteMapping("/delete")
    public void deleteShop(
            @RequestBody Shop shop) {
        shopService.delete(shop);
    }


    @GetMapping("/get/{name}")
    public List<Shop> getShopByContributor(
            @PathVariable String name) {
        return shopService.getShopsWithName(name);
    }

    @GetMapping("/get/{name}")
    public List<Shop> getShopByContributor(@RequestParam("manufacturer") String manufacturer,
                                           @PathVariable String name) {
        return shopService.getAllShopWithNameAndWithManufacturer(name, manufacturer);
    }


}
