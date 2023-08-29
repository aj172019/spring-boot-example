package com.megazone.springbootboilerplate.shop.domain.tier;

import com.megazone.springbootboilerplate.shop.domain.exception.ShopTierException;

public class Gold implements ShopTier {
    @Override
    public ShopTier up() {
        throw new ShopTierException("등급을 더 이상 올릴 수 없습니다.");
    }

    @Override
    public ShopTier down() {
        return new Silver();
    }

    @Override
    public String getCode() {
        return "Gold";
    }
}
