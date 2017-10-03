package com.fluffypets.mvc.page_objects;

import java.util.List;

public class ValidationMessage<T> {
    private T validationObject;
    private String validationMessage;
    private List<FieldStatus> fieldStatuses;

    public ValidationMessage(T validationObject, String validationMessage, List<FieldStatus> fieldStatuses) {
        this.validationObject = validationObject;
        this.validationMessage = validationMessage;
        this.fieldStatuses = fieldStatuses;
    }

    public T getValidationObject() {
        return validationObject;
    }

    public void setValidationObject(T validationObject) {
        this.validationObject = validationObject;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public List<FieldStatus> getFieldStatuses() {
        return fieldStatuses;
    }

    public void setFieldStatuses(List<FieldStatus> fieldStatuses) {
        this.fieldStatuses = fieldStatuses;
    }
}
