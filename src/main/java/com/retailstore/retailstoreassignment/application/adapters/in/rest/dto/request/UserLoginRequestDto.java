package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.valildator.EmailCriteria;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.valildator.PasswordCriteria;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDto {

   @EmailCriteria(message = "Email is not valid!")
   private String email;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @PasswordCriteria(message = "Password is not valid. It should be minimum 8 characters.")
   private String password;
}
