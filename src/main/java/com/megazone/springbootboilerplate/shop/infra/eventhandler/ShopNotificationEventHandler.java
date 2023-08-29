package com.megazone.springbootboilerplate.shop.infra.eventhandler;

import com.megazone.springbootboilerplate.common.event.EventHandler;
import com.megazone.springbootboilerplate.shop.domain.ShopTierEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class ShopNotificationEventHandler implements EventHandler<ShopTierEvent> {

    @Async
    @Transactional
    @TransactionalEventListener(ShopTierEvent.class)
    @Override
    public void handle(ShopTierEvent event) {
        log.info("ShopId: {} {} 알림 전송", event.id(), event.type());
    }
}
