package com.megazone.springbootboilerplate.shop.service.dto.response;

import com.megazone.springbootboilerplate.shop.domain.Shop;

public record ShopResponse(
        Long id,
        String name,
        String address,
        String detailAddress,
        String tier
) {
    public static ShopResponse of(Shop shop) {
        return new ShopResponse(shop.getId(),
            shop.getName(),
            shop.getAddress().getAddress(),
            shop.getAddress().getDetailAddress(),
            shop.getTier().getCode());
    }
}
