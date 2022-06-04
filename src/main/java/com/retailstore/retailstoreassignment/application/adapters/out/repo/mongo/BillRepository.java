package com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo;

import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ilkayaktas on 3.06.2022 at 00:02.
 */

@Repository
public interface BillRepository extends MongoRepository<Bill, String> {
	List<Bill> findBillByUserId(String userId);
}
