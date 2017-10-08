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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationMessage)) return false;

        ValidationMessage<?> that = (ValidationMessage<?>) o;

        if (!getValidationObject().equals(that.getValidationObject())) return false;
        if (!getValidationMessage().equals(that.getValidationMessage())) return false;

        if (that.getFieldStatuses().size()!=this.getFieldStatuses().size())return false;
        for (int i=0;i<this.getFieldStatuses().size();i++) {
            if (!this.getFieldStatuses().get(i).equals(that.getFieldStatuses().get(i)))return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = getValidationObject().hashCode();
        result = 31 * result + getValidationMessage().hashCode();
        result = 31 * result + getFieldStatuses().hashCode();
        return result;
    }
}
