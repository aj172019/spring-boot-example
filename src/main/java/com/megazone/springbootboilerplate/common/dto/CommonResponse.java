package com.megazone.springbootboilerplate.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

public record CommonResponse<T>(
    String code,
    String message,
    @JsonInclude(JsonInclude.Include.NON_NULL) T data,
    @JsonInclude(JsonInclude.Include.NON_NULL) List<BindErrorResponse> errors
) {

    public CommonResponse(String code, String message, T data) {
        this(code, message, data, null);
    }

    public CommonResponse(String code, String message, List<BindErrorResponse> error) {
        this(code, message, null, error);
    }

    public static <T> ResponseEntity<CommonResponse<T>> ok(T data) {
        return withData(data, CommonResponseType.OK);
    }

    public static <T> ResponseEntity<CommonResponse<T>> created(T data, String locationPath) {
        CommonResponse<T> response = new CommonResponse<>(CommonResponseType.CREATE.getCode(), CommonResponseType.CREATE.getMessage(), data);
        return ResponseEntity.created(URI.create(locationPath)).body(response);
    }

    public static <T> ResponseEntity<CommonResponse<T>> withData(T data, CommonResponseType responseType) {
        return withData(data, responseType, responseType.getStatus().value());
    }

    public static <T> ResponseEntity<CommonResponse<T>> withData(T data, CommonResponseType responseType, int status) {
        return of(responseType.getCode(), responseType.getMessage(), status, data);
    }

    public static <T> ResponseEntity<CommonResponse<T>> of(String code, String message, int status) {
        return of(code, message, status, null);
    }

    public static <T> ResponseEntity<CommonResponse<T>> of(String code, String message, int status, T data) {
        CommonResponse<T> response = new CommonResponse<>(code, message, data);
        return ResponseEntity.status(status).body(response);
    }

    public static ResponseEntity<CommonResponse<Void>> error(CommonResponseType responseType, Exception e) {
        return error(responseType, responseType.getStatus().value(), e);
    }

    public static ResponseEntity<CommonResponse<Void>> error(CommonResponseType responseType, int status, Exception e) {
        return error(responseType.getCode(), e.getMessage(), status, null);
    }

    public static ResponseEntity<CommonResponse<Void>> error(CommonResponseType responseType, List<BindErrorResponse> bindErrors) {
        return error(responseType.getCode(), responseType.getMessage(), responseType.getStatus().value(), bindErrors);
    }

    public static <T> ResponseEntity<CommonResponse<T>> error(String code, String message, int status, List<BindErrorResponse> bindErrors) {
        CommonResponse<T> response = new CommonResponse<>(code, message, bindErrors);
        return ResponseEntity.status(status).body(response);
    }
}
