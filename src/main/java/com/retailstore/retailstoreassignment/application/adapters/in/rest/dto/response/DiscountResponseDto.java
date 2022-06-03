package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DiscountResponseDto {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double discount;

	private String billId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double totalPrice;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double totalPriceAfterDiscount;

}
