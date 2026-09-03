package com.techcrack.bookwise.validations;

import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.repository.UserRepository;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.stereotype.Component;

@Component
public class UserServiceValidation extends AbstractLogger<UserServiceValidation> {
    private final UserRepository repository;

    public UserServiceValidation(UserRepository repository) {
        super(UserServiceValidation.class);
        this.repository = repository;
    }

    public Errors validateUserData(Users user) {
        logger.debug("Validating User data process started");
        Errors errors = new Errors();

        if (!isValidPassWord(user.getPassword())) {
            errors.addErrorMessage("Invalid Password : Ensure Password length is min 8 and contains alphanumeric and special letters");
        }

        if (!isValidContact(user.getContact())) {
            errors.addErrorMessage("Invalid Contact : Ensure Contact has 10 digit or contains only digits");
        }

        if (isUserAlreadyExists(user)) {
            findFailedConstraint(user, errors);
        }

        logger.debug("Validating user data is completed");
        return errors;
    }

    private void findFailedConstraint(Users users, Errors errors) {
        if (repository.existsByUsername(users.getUsername())) {
            errors.addErrorMessage("User name already exists. Try Different");
        }

        if (repository.existsByEmail(users.getEmail())) {
            errors.addErrorMessage("Email already exists. Try Different");
        }

        if (repository.existsByContact(users.getContact())) {
            errors.addErrorMessage("Contact already exists. Try Different");
        }
    }

    public boolean isUserAlreadyExists(Users users) {
        if (users == null) {
            return false;
        }

        return !repository.findExistingUsers(users.getUsername(), users.getEmail(), users.getContact()).isEmpty();
    }

    public boolean isValidPassWord(String password) {
        logger.debug("Password Validating Process Started for {}", password);

        if (password.length() < 8) {
            return false;
        }

        boolean isDigit = false, isAlpha = false, isSpl = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                isDigit = true;
            } else if (Character.isAlphabetic(ch))  {
                isAlpha = true;
            } else {
                isSpl = true;
            }

            if (isDigit && isAlpha && isSpl) {
                logger.debug("Password is Validated successfully.");
                return true;
            }
        }

        logger.debug("Password Validation Failed Due to IsDigit = {}, IsAlpha = {}, IsSpecialCharacter = {}", isDigit, isAlpha, isSpl);

        return false;
    }

    public boolean isValidContact(String contact) {
        if (contact.length() != 10) {
            return false;
        }

        for (char ch : contact.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }

        return true;
    }
}
