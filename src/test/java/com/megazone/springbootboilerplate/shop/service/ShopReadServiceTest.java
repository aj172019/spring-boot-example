package com.megazone.springbootboilerplate.shop.service;

import com.megazone.springbootboilerplate.shop.service.dto.response.query.ReviewQuery;
import com.megazone.springbootboilerplate.shop.service.dto.response.query.ShopWithReviewsQuery;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopReadServiceTest {

    @Autowired
    ShopReadService shopReadService;

    @DisplayName("MyBatis 쿼리 테스트")
    @Test
    void queryTest() {
        ReviewQuery review1 = new ReviewQuery(1L, "좋아요");
        ReviewQuery review2 = new ReviewQuery(2L, "싫어요");
        ReviewQuery review3 = new ReviewQuery(3L, "나가주세요");

        ShopWithReviewsQuery queryResponse = shopReadService.findShopWithReviewsById(1L);

        Assertions.assertThat(queryResponse.getReviews()).containsExactly(review1, review2, review3);
    }
}
