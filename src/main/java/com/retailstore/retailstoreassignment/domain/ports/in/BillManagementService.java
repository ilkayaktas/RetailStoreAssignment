package com.retailstore.retailstoreassignment.domain.ports.in;

import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;

import java.util.List;

public interface BillManagementService {
   Bill getBill(String billId) throws BillNotFoundException;
   List<Bill> getUserBills(String userId);
   List<Bill> getBills();

	Bill createBill(Bill bill);

}
