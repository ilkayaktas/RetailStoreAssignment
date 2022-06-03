package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDto {
	private String id;

	private String createdAt;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double price;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private User user;

	private List<ItemResponseDto> itemList;

}
