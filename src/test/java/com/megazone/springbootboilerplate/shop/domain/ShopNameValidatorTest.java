package com.megazone.springbootboilerplate.shop.domain;

import com.megazone.springbootboilerplate.shop.domain.exception.ShopNameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ShopNameValidatorTest {

    @DisplayName("2글자 미만 또는 10글자 초과일 때 예외를 던진다.")
    @ValueSource(strings = {"a", "aaaaaaaaaaa"})
    @ParameterizedTest(name = "입력값: {0}")
    void validateTest(String input) {
        ShopNameValidator shopNameValidator = new ShopNameValidator();

        Assertions.assertThatThrownBy(() -> shopNameValidator.validate(input)).isInstanceOf(ShopNameException.class);
    }
}
