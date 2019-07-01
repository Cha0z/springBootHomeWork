package com.homework.home.web.controller;

import com.homework.home.models.Shop;
import com.homework.home.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopController {


    @Autowired
    private ShopService shopService;


    @GetMapping("/getallshops")
    public String getAllShop(Model model) {
        List<Shop> allShops = shopService.getAll();
        model.addAttribute("shopList", allShops);
        return "shop";
    }


}
