package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.valildator;


import com.retailstore.retailstoreassignment.config.AppLogger;
import com.retailstore.retailstoreassignment.config.Constants;
import org.slf4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailCriteriaValidator implements ConstraintValidator<EmailCriteria, String> {
    private static final int PASSWORD_LENGTH = 4;
    private String message;
    Logger logger = AppLogger.getLogger(EmailCriteriaValidator.class);

    @Override
    public void initialize(EmailCriteria constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if(email == null || email.isBlank()){
            return false;
        }

        if ( !Pattern.compile(Constants.emailValidationPattern)
                .matcher(email)
                .matches()){
            logger.error("Email is not valid");
            return false;
        }

        return true;
    }


}