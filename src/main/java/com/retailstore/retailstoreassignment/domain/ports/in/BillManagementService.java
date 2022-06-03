package com.retailstore.retailstoreassignment.domain.ports.in;

import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import com.retailstore.retailstoreassignment.domain.model.entity.Item;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.ItemNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;

import java.util.List;

public interface BillManagementService {
   Bill getBill(String billId) throws BillNotFoundException;
   List<Bill> getUserBills(String userId);
   List<Bill> getBills();

	Bill createBill(Bill bill) throws UserNotFoundException;

	Bill addItemIntoBill(String billId, Item item) throws BillNotFoundException;

	Bill deleteItemFromBill(String billId, String itemId) throws BillNotFoundException, ItemNotFoundException;
}
