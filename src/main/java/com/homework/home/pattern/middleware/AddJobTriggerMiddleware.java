package com.homework.home.pattern.middleware;

import com.homework.home.models.Shop;


/**
 * задачу несколько упростил. У меня когда-то была такая задача, но выполнял я ее по другому
 * захотелось сделать через этот паттерн
 */

public class AddJobTriggerMiddleware extends Middleware {

  @Override
  public boolean check(Shop shop) {

      //createTriggerForShop

    return checkNext(shop);
  }
}
