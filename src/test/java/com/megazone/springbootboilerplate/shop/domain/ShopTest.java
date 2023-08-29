package com.megazone.springbootboilerplate.shop.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShopTest {

    @DisplayName("이름 길이 제한 테스트")
    @Test
    void createTest() {
        Assertions.assertThatThrownBy(() -> new Shop("A"))
            .isInstanceOf(Exception.class);
    }
}
