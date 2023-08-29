package com.megazone.springbootboilerplate.shop.exhandler;

import com.megazone.springbootboilerplate.common.dto.CommonResponse;
import com.megazone.springbootboilerplate.shop.domain.exception.ShopTierException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(ShopTierException.class)
    public ResponseEntity<CommonResponse<Void>> error(ShopTierException e) {
        return CommonResponse.of("T-1", e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

}
