package com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo;

import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;
import com.retailstore.retailstoreassignment.domain.ports.out.repo.BillRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillRepositoryPortImpl implements BillRepositoryPort {

	private BillRepository billRepository;

	@Autowired
	public BillRepositoryPortImpl(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	@Override
	public Bill save(Bill bill) {
		return billRepository.save(bill);
	}

	@Override
	public Bill findById(String billId) throws BillNotFoundException {
		Optional<Bill> bill = billRepository.findById(billId);

		if (bill.isPresent()){
			return bill.get();
		}

		throw new BillNotFoundException("Bill not found");
	}

	@Override
	public List<Bill> findAllBills() {
		return billRepository.findAll();
	}

	@Override
	public List<Bill> findUserBills(String userId) {
		return billRepository.findBillByUserId(userId);
	}
}
