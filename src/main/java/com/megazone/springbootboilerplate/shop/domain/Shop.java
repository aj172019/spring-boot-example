package com.megazone.springbootboilerplate.shop.domain;

import com.megazone.springbootboilerplate.common.event.Events;
import com.megazone.springbootboilerplate.shop.domain.tier.Bronze;
import com.megazone.springbootboilerplate.shop.domain.tier.ShopTier;
import com.megazone.springbootboilerplate.shop.domain.tier.ShopTierType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Shop {

    private static final ShopNameValidator NAME_VALIDATOR = new ShopNameValidator();

    private Long id;
    private String name;
    private ShopAddress address;
    private ShopTier tier; // 인터페이스 구현체 상태 패턴 적용
    private ShopTierType tierType; // ENUM 상태 패턴 적용

    public Shop(String name) {
        this(name, "", "");
    }

    public Shop(String name, String address, String detailAddress) {
        NAME_VALIDATOR.validate(name);
        this.name = name;
        this.address = new ShopAddress(address, detailAddress);
        this.tier = new Bronze();
        this.tierType = ShopTierType.BRONZE;
    }

    public void upgrade() {
        tier = tier.up();
        tierType = tierType.up();
        Events.raise(new ShopTierEvent(id, "upgrade"));
    }

    public void downgrade() {
        tier = tier.down();
        tierType = tierType.down();
        Events.raise(new ShopTierEvent(id, "downgrade"));
    }
}
