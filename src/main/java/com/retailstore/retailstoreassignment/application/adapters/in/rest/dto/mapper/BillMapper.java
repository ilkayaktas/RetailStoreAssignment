package com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.mapper;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.BillRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.ItemRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.BillResponseDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.DiscountResponseDto;
import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import com.retailstore.retailstoreassignment.domain.model.entity.Item;
import com.retailstore.retailstoreassignment.domain.model.vo.DiscountVO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BillMapper {
	BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);

	BillResponseDto toBillResponseDto(Bill user);

	Bill toBill(BillRequestDto billRequestDto);

	Item toItem(ItemRequestDto billRequestDto);

	DiscountResponseDto toDiscountResponseDto(DiscountVO discount);
}
