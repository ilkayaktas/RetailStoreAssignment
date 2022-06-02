package com.retailstore.retailstoreassignment.domain.service;

import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;
import com.retailstore.retailstoreassignment.domain.ports.in.BillManagementService;
import com.retailstore.retailstoreassignment.domain.ports.out.repo.BillRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillManagementServiceImpl implements BillManagementService {
	private BillRepositoryPort billRepositoryPort;

	@Autowired
	public BillManagementServiceImpl(BillRepositoryPort billRepositoryPort) {
		this.billRepositoryPort = billRepositoryPort;
	}

	@Override
	public Bill getBill(String billId) throws BillNotFoundException {
		return billRepositoryPort.findById(billId);
	}

	@Override
	public List<Bill> getUserBills(String userId) {
		return billRepositoryPort.findUserBills(userId);
	}

	@Override
	public List<Bill> getBills() {
		return billRepositoryPort.findAllBills();
	}

	@Override
	public Bill createBill(Bill bill) {
		bill.setCreatedAt(LocalDateTime.now());
		return billRepositoryPort.save(bill);
	}
}
