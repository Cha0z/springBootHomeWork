package com.homework.home.pattern.middleware;

import com.homework.home.models.Shop;
import com.homework.home.models.ShopStatus;
import com.homework.home.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateShopNameMiddleware extends Middleware {

  @Autowired
  private ShopService shopService;

  @Override
  public boolean check(Shop shop) {

    if (checkIfNull(shop)) {
      shop.setStatus(ShopStatus.CLOSED);
    }

    return checkNext(shop);
  }

  private boolean checkIfNull(Shop shop) {
    return shop != null;
  }
}
