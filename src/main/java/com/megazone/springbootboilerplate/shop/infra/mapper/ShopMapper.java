package com.megazone.springbootboilerplate.shop.infra.mapper;

import com.megazone.springbootboilerplate.shop.domain.Shop;
import com.megazone.springbootboilerplate.shop.service.dto.response.query.ShopWithReviewsQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShopMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("""
            INSERT INTO shop (shop_name, shop_address, shop_detail_address, shop_tier, shop_tier_code)
            VALUES (#{name}, #{address.address}, #{address.detailAddress}, #{tier}, #{tierType})
            """)
    void save(Shop shop);

    @Update("""
            UPDATE shop 
            SET shop_name = #{name}, shop_tier = #{tier}, shop_tier_code = #{tierType}
            WHERE shop_id = #{id}
            """)
    void update(Shop shop);

    @ResultMap("shopRowMapper")
    @Select("""
            SELECT * 
            FROM shop
            """)
    List<Shop> findAll();

    @ResultMap("shopRowMapper")
    @Select("""
            SELECT * 
            FROM shop
            WHERE shop_id = #{id}
            """)
    Shop findById(@Param("id") Long id);

    @ResultMap("shopRowMapper")
    @Select("""
            SELECT * 
            FROM shop
            WHERE shop_name = #{name}
            """)
    Shop findByName(@Param("name") String name);

    ShopWithReviewsQuery findShopReviewsById(Long id);
}
