package com.megazone.springbootboilerplate.shop.service.dto.response.query;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class ReviewQuery {
    private Long id;
    private String contents;

    public ReviewQuery(Long id, String contents) {
        this.id = id;
        this.contents = contents;
    }
}
