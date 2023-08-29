package com.megazone.springbootboilerplate.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonResponseType {
    CREATE("A-1", "생성됨", HttpStatus.CREATED),
    OK("A-2", "정보 조회", HttpStatus.OK),
    UPDATE("A-3", "정보 업데이트", HttpStatus.OK),
    BAD_REQUEST("E-0", "요청이 올바르지 않음", HttpStatus.BAD_REQUEST),
    BIND_ERROR("E-1", "바인딩 에러", HttpStatus.BAD_REQUEST),
    NOT_FOUND("E-2", "정보를 찾을 수 없음", HttpStatus.NOT_FOUND),
    DUPLICATE("E-3", "중복된 데이터", HttpStatus.CONFLICT);

    private final String code;
    private final String message;
    private final HttpStatus status;

    CommonResponseType(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
