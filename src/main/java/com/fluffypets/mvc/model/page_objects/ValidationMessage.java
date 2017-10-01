package com.fluffypets.mvc.model.page_objects;

import java.util.List;

public class ValidationMessage {
    private Object validationObject;
    private String validationMessage;
    private List<FieldStatus> fieldStatuses;

    class FieldStatus {
        private boolean status;
        private String name;

        public FieldStatus(boolean status, String name) {
            this.status = status;
            this.name = name;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public ValidationMessage(Object validationObject, String validationMessage, List<FieldStatus> fieldStatuses) {
        this.validationObject = validationObject;
        this.validationMessage = validationMessage;
        this.fieldStatuses = fieldStatuses;
    }

    public Object getValidationObject() {
        return validationObject;
    }

    public void setValidationObject(Object validationObject) {
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
