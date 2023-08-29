package com.megazone.springbootboilerplate.shop.service.dto.request;

import com.megazone.springbootboilerplate.common.validation.NonSpecialCharacters;
import com.megazone.springbootboilerplate.shop.domain.ShopName;
import jakarta.validation.constraints.NotEmpty;

public record ShopCreateRequest(
    @ShopName
    String name,

    @NotEmpty
    @NonSpecialCharacters
    String address,

    @NotEmpty
    @NonSpecialCharacters
    String detailAddress
) {
}
