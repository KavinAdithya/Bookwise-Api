package com.techcrack.bookwise.exceptions.templates;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    private final List<String> errors;

    public Errors() {
        this.errors = new ArrayList<>();
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void addErrorMessage(String message) {
        errors.add(message + ". ");
    }

    public int errorsCount() {
        return errors.size();
    }

    public String getData() {
        StringBuilder errorData = new StringBuilder();

        errors.forEach(errorData::append);

        return errorData.toString();
    }
}
