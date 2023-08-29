package com.megazone.springbootboilerplate.shop.domain;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.megazone.springbootboilerplate.shop.domain.ShopNameValidator.DEFAULT_MESSAGE;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ShopNameValidator.class})
public @interface ShopName {

    String message() default DEFAULT_MESSAGE;
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
