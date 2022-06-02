package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDto {
	private String id;

	private String createdAt;

	private Double price;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private User user;
}
