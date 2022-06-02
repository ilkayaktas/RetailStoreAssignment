package com.retailstore.retailstoreassignment.application.adapters.in.rest.controller;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.BillRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.BillResponseDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.facade.BillManagementFacade;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1")
@Validated
public class BillRestController extends BaseRestController{

	private BillManagementFacade billManagementFacade;

	@Autowired
	public BillRestController(BillManagementFacade billManagementFacade) {
		this.billManagementFacade = billManagementFacade;
	}

	@GetMapping(value = "/bills/{billId}")
	public ResponseEntity<BillResponseDto> getBill(@Valid @PathVariable String billId) throws BillNotFoundException {

		BillResponseDto bill = billManagementFacade.getBill(billId);
		return ResponseEntity.ok(bill);
	}

	@GetMapping(value = "/bills/users/{userId}")
	public ResponseEntity<List<BillResponseDto>> getUserBills(@Valid @PathVariable String userId) {

		List<BillResponseDto> userBills = billManagementFacade.getUserBills(userId);
		return ResponseEntity.ok(userBills);
	}

	@PostMapping(value = "/bills")
	public ResponseEntity<BillResponseDto> createBill(@Valid
														  @NotNull(message = "Bill can't be empty.")
														  @RequestBody BillRequestDto billRequestDto){

		BillResponseDto bill = billManagementFacade.createBill(billRequestDto);
		return ResponseEntity.ok(bill);
	}

	@GetMapping(value = "/bills")
	public ResponseEntity<List<BillResponseDto>> getAllBills() {

		List<BillResponseDto> bills = billManagementFacade.getBills();
		return ResponseEntity.ok(bills);
	}

}
