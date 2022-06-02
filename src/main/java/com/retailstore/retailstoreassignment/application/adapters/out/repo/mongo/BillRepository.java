package com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo;

import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ilkayaktas on 3.06.2022 at 00:02.
 */

public interface BillRepository extends MongoRepository<Bill, String> {
	List<Bill> findBillByUserId(String userId);

	// TODO findAll with aggregation
}
