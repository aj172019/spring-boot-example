package com.megazone.springbootboilerplate.common.validation;

import java.util.function.Supplier;
import java.util.regex.Pattern;

public class SpecialCharacterValidator implements CustomValidator<NonSpecialCharacters, String> {

    private static final Pattern PATTERN = Pattern.compile("[가-힣0-9a-zA-Z\\-\\s]*");
    protected static final String DEFAULT_MESSAGE = "특수문자는 사용할 수 없습니다.";

    @Override
    public boolean isValid(String value) {
        return PATTERN.matcher(value).matches();
    }

    @Override
    public Supplier<? extends RuntimeException> throwIfInvalidValue() {
        return () -> new IllegalArgumentException(DEFAULT_MESSAGE);
    }
}
