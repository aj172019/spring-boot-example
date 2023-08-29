package com.megazone.springbootboilerplate.shop.domain.exception;

import com.megazone.springbootboilerplate.common.exception.InvalidValueException;

public class ShopNameException extends InvalidValueException {

    public ShopNameException(String message) {
        super(message);
    }
}
