package com.techcrack.bookwise.utils.exceptions.templates;

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


    public String getData() {
        StringBuilder errorData = new StringBuilder();

        errors.forEach(errorData::append);

        return errorData.toString();
    }
}
