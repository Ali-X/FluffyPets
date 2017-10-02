package com.fluffypets.servicies.validators;

import com.fluffypets.mvc.model.page_objects.ValidationMessage;

public interface Validator<T> {
    ValidationMessage<T> validate(T t);
}
