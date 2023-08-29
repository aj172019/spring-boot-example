package com.megazone.springbootboilerplate.shop.infra.repository;

import com.megazone.springbootboilerplate.shop.domain.Shop;
import com.megazone.springbootboilerplate.shop.domain.ShopRepository;
import com.megazone.springbootboilerplate.shop.infra.mapper.ShopMapper;
import com.megazone.springbootboilerplate.shop.service.dto.response.query.ShopWithReviewsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ShopRepositoryImpl implements ShopRepository {
    private final ShopMapper shopMapper;

    @Override
    public Shop save(Shop shop) {
        shopMapper.save(shop);
        return shop;
    }

    @Override
    public List<Shop> findAll() {
        return shopMapper.findAll();
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return Optional.ofNullable(shopMapper.findById(id));
    }

    @Override
    public Optional<ShopWithReviewsQuery> findShopReviewsById(Long id) {
        return Optional.ofNullable(shopMapper.findShopReviewsById(id));
    }

    @Override
    public Optional<Shop> findByName(String name) {
        return Optional.ofNullable(shopMapper.findByName(name));
    }

    @Override
    public void update(Shop shop) {
        shopMapper.update(shop);
    }
}
