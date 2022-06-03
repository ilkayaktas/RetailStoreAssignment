package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BillRequestDto {
	@NotBlank(message = "User id can't be empty.")
	private String userId;

	@NotNull(message = "At least one item should be inserted to bill.")
	private List<ItemRequestDto> itemList;

}
