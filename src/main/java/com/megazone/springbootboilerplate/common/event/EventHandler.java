package com.megazone.springbootboilerplate.common.event;

public interface EventHandler<T> {

    void handle(T event);
}
