package com.fluffypets.validators;

import com.fluffypets.mvc.page_objects.ValidationMessage;

public interface Validator<T> {
    ValidationMessage<T> validate(T t);
}
