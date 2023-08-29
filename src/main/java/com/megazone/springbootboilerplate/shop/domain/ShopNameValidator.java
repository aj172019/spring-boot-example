package com.megazone.springbootboilerplate.shop.domain;

import com.megazone.springbootboilerplate.common.validation.CustomValidator;
import com.megazone.springbootboilerplate.shop.domain.exception.ShopNameException;
import java.util.function.Supplier;

public class ShopNameValidator implements CustomValidator<ShopName, String> {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 10;
    protected static final String DEFAULT_MESSAGE = "Shop 이름은 " + MIN_LENGTH + "글자 이상 " + MAX_LENGTH + "글자 이하이어야 합니다.";

    @Override
    public boolean isValid(String name) {
        return name != null && name.length() >= MIN_LENGTH && name.length() <= MAX_LENGTH;
    }

    @Override
    public Supplier<? extends RuntimeException> throwIfInvalidValue() {
        return () -> new ShopNameException(DEFAULT_MESSAGE);
    }
}
