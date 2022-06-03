package com.retailstore.retailstoreassignment.domain.service;

import com.retailstore.retailstoreassignment.config.Constants;
import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import com.retailstore.retailstoreassignment.domain.model.entity.Item;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.enums.ItemType;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.ItemNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.vo.DiscountVO;
import com.retailstore.retailstoreassignment.domain.ports.in.BillManagementService;
import com.retailstore.retailstoreassignment.domain.ports.out.repo.BillRepositoryPort;
import com.retailstore.retailstoreassignment.domain.ports.out.repo.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BillManagementServiceImpl implements BillManagementService {
	private BillRepositoryPort billRepositoryPort;
	private UserRepositoryPort userRepositoryPort;

	@Autowired
	public BillManagementServiceImpl(BillRepositoryPort billRepositoryPort, UserRepositoryPort userRepositoryPort) {
		this.billRepositoryPort = billRepositoryPort;
		this.userRepositoryPort = userRepositoryPort;
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
	public Bill createBill(Bill bill) throws UserNotFoundException {

		userRepositoryPort.findById(bill.getUserId());

		bill.setCreatedAt(LocalDateTime.now());
		bill.getItemList().forEach(item -> {
			item.setId(UUID.randomUUID().toString().replace("-",""));
			item.setCreatedAt(LocalDateTime.now());
		});
		return billRepositoryPort.save(bill);
	}

	@Override
	public Bill addItemIntoBill(String billId, Item item) throws BillNotFoundException {

		item.setId(UUID.randomUUID().toString().replace("-",""));
		item.setCreatedAt(LocalDateTime.now());

		Bill bill = billRepositoryPort.findById(billId);
		bill.getItemList().add(item);
		return billRepositoryPort.save(bill);
	}

	@Override
	public Bill deleteItemFromBill(String billId, String itemId) throws BillNotFoundException, ItemNotFoundException {
		Bill bill = billRepositoryPort.findById(billId);

		boolean isItemDeleted = false;
		for (int i = 0; i < bill.getItemList().size(); i++) {
			if (bill.getItemList().get(i).getId().equals(itemId)){
				bill.getItemList().remove(i);
				isItemDeleted = true;
				break;
			}
		}

		if (!isItemDeleted)throw new ItemNotFoundException("Item not found");

		return billRepositoryPort.save(bill);
	}

	@Override
	public DiscountVO calculateTotalDiscount(String billId) throws BillNotFoundException, UserNotFoundException {
		Bill bill = billRepositoryPort.findById(billId);
		User user = userRepositoryPort.findById(bill.getUserId());

		DiscountVO discount = calculateDiscount(bill.getItemList(), user);
		discount.setBillId(billId);
		return discount;
	}

	@Override
	public Bill getTotalPriceAfterDiscount(String billId) throws BillNotFoundException, UserNotFoundException {
		Bill bill = billRepositoryPort.findById(billId);
		User user = userRepositoryPort.findById(bill.getUserId());

		DiscountVO discount = calculateDiscount(bill.getItemList(), user);
		bill.setPrice(discount.getTotalPriceAfterDiscount());
		return billRepositoryPort.save(bill);
	}

	protected DiscountVO calculateDiscount(List<Item> itemList, User user){
		DiscountVO discountVO = new DiscountVO(0d, null, 0d, 0d);


		for (Item item : itemList) {
			discountVO.setTotalPrice(discountVO.getTotalPrice()+item.getPrice());

			if (!item.getItemType().equals(ItemType.GROCERIES)){
				switch (user.getUserType()){
					case EMPLOYEE -> {
						discountVO.setDiscount(discountVO.getDiscount()+ (item.getPrice() * 0.3) );
					}
					case CUSTOMER -> {
						if (findYearDifferenceFromNow(user.getCreatedAt()) > 2){
							discountVO.setDiscount(discountVO.getDiscount()+ (item.getPrice() * 0.05) );
						}
					}
					case AFFILIATE -> {
						discountVO.setDiscount(discountVO.getDiscount()+ (item.getPrice() * 0.1) );
					}
				}
			}
		}

		discountVO.setTotalPriceAfterDiscount(
				discountVO.getTotalPrice() - discountVO.getDiscount()
		);

		double repetetiveDiscount = calculatediscountForRepetetiveAmount(discountVO.getTotalPriceAfterDiscount());

		discountVO.setTotalPriceAfterDiscount(discountVO.getTotalPriceAfterDiscount() - repetetiveDiscount);
		discountVO.setDiscount(discountVO.getDiscount() + repetetiveDiscount);

		return discountVO;
	}

	protected Double calculatediscountForRepetetiveAmount(Double totalPrice){
		int division = (int) (totalPrice / Constants.DISCOUNT_FOR_EACH_REPETETIVE_PERIOD);

		return (double) (division * Constants.DISCOUNT_PRICE_FOR_EACH_REPETETIVE_VALUE);
	}

	protected Long findYearDifferenceFromNow(LocalDateTime userCreationTime){
		LocalDateTime toDateTime = LocalDateTime.now();

		LocalDateTime fromDateTime = LocalDateTime.from( userCreationTime );

		long years = fromDateTime.until( toDateTime, ChronoUnit.YEARS );

		return years;
	}
}
