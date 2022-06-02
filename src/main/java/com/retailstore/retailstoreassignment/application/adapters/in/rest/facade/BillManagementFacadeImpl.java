package com.retailstore.retailstoreassignment.application.adapters.in.rest.facade;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.mapper.BillMapper;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.BillRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.BillResponseDto;
import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;
import com.retailstore.retailstoreassignment.domain.ports.in.BillManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillManagementFacadeImpl implements BillManagementFacade{

	private BillManagementService billManagementService;

	@Autowired
	public BillManagementFacadeImpl(BillManagementService billManagementService) {
		this.billManagementService = billManagementService;
	}

	@Override
	public BillResponseDto getBill(String billId) throws BillNotFoundException {
		Bill bill = billManagementService.getBill(billId);
		return BillMapper.INSTANCE.toBillResponseDto(bill);
	}

	@Override
	public List<BillResponseDto> getUserBills(String userId) {
		List<Bill> userBills = billManagementService.getUserBills(userId);

		return userBills.stream().map(BillMapper.INSTANCE::toBillResponseDto).toList();
	}

	@Override
	public List<BillResponseDto> getBills() {
		List<Bill> bills = billManagementService.getBills();

		return bills.stream().map(BillMapper.INSTANCE::toBillResponseDto).toList();
	}

	@Override
	public BillResponseDto createBill(BillRequestDto billRequestDto) {
		Bill bill = BillMapper.INSTANCE.toBill(billRequestDto);
		Bill createdBill = billManagementService.createBill(bill);
		return BillMapper.INSTANCE.toBillResponseDto(createdBill);
	}
}
