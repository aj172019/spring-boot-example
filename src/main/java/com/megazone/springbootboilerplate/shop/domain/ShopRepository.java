package com.megazone.springbootboilerplate.shop.domain;


import com.megazone.springbootboilerplate.shop.service.dto.response.query.ShopWithReviewsQuery;
import java.util.List;
import java.util.Optional;

public interface ShopRepository {
    Shop save(Shop shop);

    List<Shop> findAll();

    Optional<Shop> findById(Long id);

    Optional<ShopWithReviewsQuery> findShopReviewsById(Long id);

    Optional<Shop> findByName(String name);

    void update(Shop shop);
}
