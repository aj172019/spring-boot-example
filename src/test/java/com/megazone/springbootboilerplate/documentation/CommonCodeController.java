package com.megazone.springbootboilerplate.documentation;

import com.megazone.springbootboilerplate.common.dto.CommonResponseType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CommonCodeController {

    @GetMapping(value = "/codes")
    public Map<String, CodeResponse> getCodes() {
        return Arrays.stream(CommonResponseType.values())
            .collect(Collectors.toMap(Enum::name, CodeResponse::of));
    }

    public record CodeResponse(
        String code,
        String message,
        int status
    ) {

        public static CodeResponse of(CommonResponseType responseType) {
            return new CodeResponse(
                responseType.getCode(),
                responseType.getMessage(),
                responseType.getStatus().value());
        }
    }
}
