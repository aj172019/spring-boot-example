package com.megazone.springbootboilerplate.shop.domain;

import com.megazone.springbootboilerplate.common.validation.SpecialCharacterValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ShopAddress {

    private static final SpecialCharacterValidator SPECIAL_CHARACTER_VALIDATOR = new SpecialCharacterValidator();

    private String address;
    private String detailAddress;

    public ShopAddress(String address, String detailAddress) {
        SPECIAL_CHARACTER_VALIDATOR.validate(address);
        SPECIAL_CHARACTER_VALIDATOR.validate(detailAddress);
        this.address = address;
        this.detailAddress = detailAddress;
    }
}
