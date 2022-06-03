package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response;

import com.retailstore.retailstoreassignment.domain.model.enums.ItemType;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {
	private String id;

	private String name;

	private String createdAt;

	private Double price;

	private ItemType itemType;

}
