package com.megazone.springbootboilerplate.shop.domain.tier;

import com.megazone.springbootboilerplate.shop.domain.exception.ShopTierException;
import lombok.Getter;

public enum ShopTierType implements ShopTierSpec {
    BRONZE(
        "B",
        new ShopTierSpec() {
            @Override
            public ShopTierType up() {
                return SILVER;
            }

            @Override
            public ShopTierType down() {
                throw new ShopTierException("등급을 더 이상 내릴 수 없습니다.");
            }
        }
    ),
    SILVER(
        "S",
        new ShopTierSpec() {
            @Override
            public ShopTierType up() {
                return GOLD;
            }

            @Override
            public ShopTierType down() {
                return BRONZE;
            }
        }
    ),
    GOLD(
        "G",
        new ShopTierSpec() {
            @Override
            public ShopTierType up() {
                throw new ShopTierException("등급을 더 이상 올릴 수 없습니다.");
            }

            @Override
            public ShopTierType down() {
                return SILVER;
            }
        }
    );


    @Getter
    private final String code;
    private final ShopTierSpec status;

    ShopTierType(String code, ShopTierSpec status) {
        this.code = code;
        this.status = status;
    }

    @Override
    public ShopTierType up() {
        return status.up();
    }

    @Override
    public ShopTierType down() {
        return status.down();
    }
}
