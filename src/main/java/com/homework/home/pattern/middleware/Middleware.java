package com.homework.home.pattern.middleware;

import com.homework.home.models.Shop;

public abstract class Middleware {

  private Middleware next;


  public Middleware linkWith(Middleware next) {
    this.next = next;
    return next;
  }

  public abstract boolean check(Shop shop);


  protected boolean checkNext(Shop shop) {
      return checkIfNull() || next.check(shop);
  }

  private boolean checkIfNull() {
    return next == null;
  }


}
