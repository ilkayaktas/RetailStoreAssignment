package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.valildator;


import com.retailstore.retailstoreassignment.config.Constants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordCriteriaValidator implements ConstraintValidator<PasswordCriteria, String> {

   private String message;

   @Override
   public void initialize(PasswordCriteria constraintAnnotation) {
      message = constraintAnnotation.message();
   }

   @Override
   public boolean isValid(String password, ConstraintValidatorContext context) {

      if(password == null || password.isBlank()){
         return false;
      }

      if (password.length() < Constants.PASSWORD_CRITERIA_LENGTH) return false;
      return true;
   }

}