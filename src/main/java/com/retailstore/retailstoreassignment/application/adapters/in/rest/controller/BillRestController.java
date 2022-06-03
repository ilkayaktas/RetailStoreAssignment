package com.retailstore.retailstoreassignment.application.adapters.in.rest.controller;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.BillRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.ItemRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.BillResponseDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.DiscountResponseDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.exception.UnauthorizedOperationException;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.facade.BillManagementFacade;
import com.retailstore.retailstoreassignment.domain.model.enums.UserType;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.ItemNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;
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

	// GET BILL
	@GetMapping(value = "/bills/{billId}")
	public ResponseEntity<BillResponseDto> getBill(@Valid @PathVariable String billId,
												   @RequestHeader (name="Authorization") String token) throws BillNotFoundException, UnauthorizedOperationException {

		checkAuthorization(token);

		BillResponseDto bill = billManagementFacade.getBill(billId);
		return ResponseEntity.ok(bill);
	}

	// GET USER BILLS
	@GetMapping(value = "/bills/users/{userId}")
	public ResponseEntity<List<BillResponseDto>> getUserBills(@Valid @PathVariable String userId,
															  @RequestHeader (name="Authorization") String token) throws UnauthorizedOperationException {
		checkAuthorization(token);

		List<BillResponseDto> userBills = billManagementFacade.getUserBills(userId);
		return ResponseEntity.ok(userBills);
	}

	// CREATE BILLS
	@PostMapping(value = "/bills")
	public ResponseEntity<BillResponseDto> createBill(@Valid
														  @NotNull(message = "Bill can't be empty.")
														  @RequestBody BillRequestDto billRequestDto,
														  @RequestHeader (name="Authorization") String token) throws UnauthorizedOperationException, UserNotFoundException {
		checkAuthorization(token);

		BillResponseDto bill = billManagementFacade.createBill(billRequestDto);
		return ResponseEntity.ok(bill);
	}

	// GET ALL BILLS

	@GetMapping(value = "/bills")
	public ResponseEntity<List<BillResponseDto>> getAllBills(@RequestHeader (name="Authorization") String token) throws UnauthorizedOperationException {

		checkAuthorization(token);

		List<BillResponseDto> bills = billManagementFacade.getBills();
		return ResponseEntity.ok(bills);
	}

	// ADD ITEM INTO BILL
	@PatchMapping(value = "/bills/{billId}")
	public ResponseEntity<BillResponseDto> addItemIntoBill(@Valid
														   @NotNull(message = "Bill can't be empty.")
														   @RequestBody ItemRequestDto billRequestDto,
														   @PathVariable String billId,
														   @RequestHeader (name="Authorization") String token) throws UnauthorizedOperationException, BillNotFoundException {

		checkAuthorization(token);

		BillResponseDto bill = billManagementFacade.addItemIntoBill(billId, billRequestDto);

		return ResponseEntity.ok(bill);
	}

	// DELETE ITEM FROM BILL
	@DeleteMapping(value = "/bills/{billId}/items/{itemId}")
	public ResponseEntity<BillResponseDto> deleteItemIntoBill(@PathVariable String billId,
														   @PathVariable String itemId,
														   @RequestHeader (name="Authorization") String token) throws UnauthorizedOperationException, BillNotFoundException, ItemNotFoundException {

		checkAuthorization(token);

		BillResponseDto bill = billManagementFacade.deleteItemFromBill(billId, itemId);

		return ResponseEntity.ok(bill);
	}

	// CALCULATE TOTAL PRICE AFTER DISCOUNT
	@GetMapping(value = "/bills/{billId}/price")
	public ResponseEntity<BillResponseDto> getTotalPriceAfterDiscount(@PathVariable String billId,
																		  @RequestHeader (name="Authorization") String token) throws UnauthorizedOperationException, BillNotFoundException, UserNotFoundException {

		checkAuthorization(token);

		BillResponseDto bill = billManagementFacade.getTotalPriceAfterDiscount(billId);

		return ResponseEntity.ok(bill);
	}

	// CALCULATE TOTAL DISCOUNT
	@GetMapping(value = "/bills/{billId}/discount")
	public ResponseEntity<DiscountResponseDto> getTotalDiscount(@PathVariable String billId,
																	  @RequestHeader (name="Authorization") String token) throws UnauthorizedOperationException, BillNotFoundException, UserNotFoundException {

		checkAuthorization(token);

		DiscountResponseDto bill = billManagementFacade.calculateTotalDiscount(billId);

		return ResponseEntity.ok(bill);
	}

	private void checkAuthorization(String token) throws UnauthorizedOperationException {
		String userType = getUserTypeFromToken(token);

		if(!userType.equals(UserType.ADMIN.name())){
			throw new UnauthorizedOperationException("UNAUTHORIZED OPERATION. Only Admin can do bill operations");
		}
	}

}
