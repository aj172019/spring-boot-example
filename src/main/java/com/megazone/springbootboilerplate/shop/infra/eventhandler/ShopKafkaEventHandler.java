package com.megazone.springbootboilerplate.shop.infra.eventhandler;

import com.megazone.springbootboilerplate.common.event.EventHandler;
import com.megazone.springbootboilerplate.shop.domain.ShopTierEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class ShopKafkaEventHandler implements EventHandler<ShopTierEvent> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Async
    @Transactional
    @TransactionalEventListener(ShopTierEvent.class)
    @Override
    public void handle(ShopTierEvent event) {
        kafkaTemplate.send("pub-topic", event);
    }

    @KafkaListener(topics = "sub-topic")
    public void consume(ConsumerRecord<String, String> record) {
        log.info("[이벤트 처리] key: {}, value: {}", record.key(), record.value());
    }
}
