package com.megazone.springbootboilerplate.common.config.web.exhandler;

import com.megazone.springbootboilerplate.common.dto.*;
import com.megazone.springbootboilerplate.common.exception.DuplicateDataException;
import com.megazone.springbootboilerplate.common.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonResponse<Void>> error(IllegalArgumentException e) {
        return CommonResponse.error(CommonResponseType.BAD_REQUEST, e);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommonResponse<Void>> error(BindException e) {
        List<BindErrorResponse> bindErrorResponses = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(BindErrorResponse::of)
            .toList();
        return CommonResponse.error(CommonResponseType.BIND_ERROR, bindErrorResponses);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<CommonResponse<Void>> error(DuplicateDataException e) {
        return CommonResponse.error(CommonResponseType.DUPLICATE, e);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CommonResponse<Void>> error(NotFoundException e) {
        return CommonResponse.error(CommonResponseType.NOT_FOUND, e);
    }
}
