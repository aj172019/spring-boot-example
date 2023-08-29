package com.megazone.springbootboilerplate.shop.service;

import com.megazone.springbootboilerplate.shop.domain.Shop;
import com.megazone.springbootboilerplate.shop.service.dto.response.ShopResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopFieldMapper {

    ShopFieldMapper INSTANCE = Mappers.getMapper(ShopFieldMapper.class);

    @Mapping(source = "shop.address.address", target = "address")
    @Mapping(source = "shop.address.detailAddress", target = "detailAddress")
    @Mapping(source = "shop.tier.code", target = "tier")
    ShopResponse toShopResponse(Shop shop);
}
