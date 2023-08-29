package com.megazone.springbootboilerplate.shop.domain.exception;

import com.megazone.springbootboilerplate.common.exception.NoStackTraceException;

public class ShopTierException extends NoStackTraceException {

    public ShopTierException(String message) {
        super(message);
    }
}
