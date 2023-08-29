package com.megazone.springbootboilerplate.shop.service;

import com.megazone.springbootboilerplate.common.exception.NotFoundException;
import com.megazone.springbootboilerplate.shop.domain.ShopRepository;
import com.megazone.springbootboilerplate.shop.infra.client.MemberFeignClient;
import com.megazone.springbootboilerplate.shop.service.dto.response.ShopResponse;
import com.megazone.springbootboilerplate.shop.service.dto.response.query.ShopWithReviewsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ShopReadService {

    private final ShopRepository shopRepository;
    private final MemberFeignClient memberFeignClient;

    public List<ShopResponse> findAll() {
        return shopRepository.findAll()
            .stream()
            .map(ShopResponse::of)
            .toList();
    }

    public ShopResponse findById(Long id) {
        return shopRepository.findById(id)
            .map(ShopResponse::of)
            .orElseThrow(() -> new NotFoundException(id + " Shop을 찾을 수 없습니다."));
    }

    public ShopWithReviewsQuery findShopWithReviewsById(Long id) {
        return shopRepository.findShopReviewsById(id)
            .orElseThrow(() -> new NotFoundException(id + " Shop을 찾을 수 없습니다."));
    }

    public Optional<String> getMember(int memberId) {
        return Optional.ofNullable(memberFeignClient.getMember(memberId));
    }
}
