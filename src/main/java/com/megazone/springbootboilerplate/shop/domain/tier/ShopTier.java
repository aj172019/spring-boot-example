package com.megazone.springbootboilerplate.shop.domain.tier;

public interface ShopTier {
    ShopTier up();

    ShopTier down();

    String getCode();
}
