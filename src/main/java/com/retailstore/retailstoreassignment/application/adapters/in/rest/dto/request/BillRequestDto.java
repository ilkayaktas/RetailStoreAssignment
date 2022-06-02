package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BillRequestDto {
	@NotBlank(message = "User id can't be empty")
	private String userId;

	@NotNull(message = "Price can't be empty")
	@Min(value = 0, message = "Price can't be less than zero")
	private Double price;
}
