package com.megazone.springbootboilerplate.shop.domain.tier;

import com.megazone.springbootboilerplate.shop.domain.exception.ShopTierException;

public class Bronze implements ShopTier {
    @Override
    public ShopTier up() {
        return new Silver();
    }

    @Override
    public ShopTier down() {
        throw new ShopTierException("등급을 더 이상 내릴 수 없습니다.");
    }

    @Override
    public String getCode() {
        return "Bronze";
    }
}
