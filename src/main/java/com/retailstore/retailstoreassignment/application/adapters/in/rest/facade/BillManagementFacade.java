package com.retailstore.retailstoreassignment.application.adapters.in.rest.facade;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.BillRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.ItemRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.BillResponseDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.DiscountResponseDto;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.ItemNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;

import java.util.List;

public interface BillManagementFacade {
	BillResponseDto getBill(String billId) throws BillNotFoundException;
	List<BillResponseDto> getUserBills(String userId);
	List<BillResponseDto> getBills();

	BillResponseDto createBill(BillRequestDto billRequestDto) throws UserNotFoundException;

	BillResponseDto deleteItemFromBill(String billId, String itemId) throws BillNotFoundException, ItemNotFoundException;

	BillResponseDto addItemIntoBill(String billId, ItemRequestDto billRequestDto) throws BillNotFoundException;

	DiscountResponseDto calculateTotalDiscount(String billId) throws BillNotFoundException, UserNotFoundException;

	BillResponseDto getTotalPriceAfterDiscount(String billId) throws BillNotFoundException, UserNotFoundException;

}
