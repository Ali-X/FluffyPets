package com.fluffypets.MVC.model;

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
}
