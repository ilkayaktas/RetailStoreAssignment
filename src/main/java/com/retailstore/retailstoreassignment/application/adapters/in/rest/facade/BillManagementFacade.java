package com.retailstore.retailstoreassignment.application.adapters.in.rest.facade;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.BillRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.BillResponseDto;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;

import java.util.List;

public interface BillManagementFacade {
	BillResponseDto getBill(String billId) throws BillNotFoundException;
	List<BillResponseDto> getUserBills(String userId);
	List<BillResponseDto> getBills();

	BillResponseDto createBill(BillRequestDto billRequestDto);
}
