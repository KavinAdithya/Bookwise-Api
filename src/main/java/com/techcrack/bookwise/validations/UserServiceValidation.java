package com.techcrack.bookwise.validations;

import com.techcrack.bookwise.entity.Users;
import com.techcrack.bookwise.exceptions.templates.Errors;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.stereotype.Component;

@Component
public class UserServiceValidation extends AbstractLogger<UserServiceValidation> {
    public UserServiceValidation() {
        super(UserServiceValidation.class);
    }

    public Errors validateUserData(Users user) {
        logger.debug("Validating User data process started");
        Errors errors = new Errors();

        if (!isValidPassWord(user.getPassword())) {
            errors.addErrorMessage("Invalid Password : Ensure Password length is min 8 and contains alphanumeric and special letters");
        }

        logger.debug("Validating user data is completed");
        return errors;
    }

    private boolean isValidPassWord(String password) {
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
}
