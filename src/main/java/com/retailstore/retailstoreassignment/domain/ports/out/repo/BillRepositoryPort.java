package com.retailstore.retailstoreassignment.domain.ports.out.repo;


import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;

import java.util.List;

public interface BillRepositoryPort {
	Bill save(Bill bill);

	Bill findById(String billId) throws BillNotFoundException;

	List<Bill> findAllBills();

	List<Bill> findUserBills(String userId);
}
