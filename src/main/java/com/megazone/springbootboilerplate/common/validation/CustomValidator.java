package com.megazone.springbootboilerplate.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.function.Supplier;

public interface CustomValidator<A extends Annotation, T> extends ConstraintValidator<A, T> {

    @Override
    default void initialize(A constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    default boolean isValid(T value, ConstraintValidatorContext context) {
        return isValid(value);
    }

    default void validate(T value) {
        if (!isValid(value)) {
            throw throwIfInvalidValue().get();
        }
    }

    boolean isValid(T t);

    Supplier<? extends RuntimeException> throwIfInvalidValue();
}
