package com.retailstore.retailstoreassignment.domain.model.vo;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DiscountVO {
   private Double discount;

   private String billId;

   private Double totalPrice;

   private Double totalPriceAfterDiscount;
}
