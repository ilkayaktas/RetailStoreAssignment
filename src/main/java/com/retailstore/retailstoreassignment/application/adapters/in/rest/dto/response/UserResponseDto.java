package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.valildator.EmailCriteria;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.valildator.PasswordCriteria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@ToString
public class UserResponseDto {

	private String id;

	@NotBlank(message = "User name can't be empty.")
	private String name;

	@NotBlank(message = "User last name can't be empty.")
	private String lastName;

	@EmailCriteria(message = "Email is not valid!")
	private String email;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PasswordCriteria(message = "Password is not valid. It should be minimum 8 characters.")
	private String password;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String createdAt;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String updatedAt;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean enabled;
}
