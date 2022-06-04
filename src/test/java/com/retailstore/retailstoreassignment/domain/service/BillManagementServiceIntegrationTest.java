package com.retailstore.retailstoreassignment.domain.service;

import com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo.BillRepository;
import com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo.UserRepository;
import com.retailstore.retailstoreassignment.domain.model.entity.Bill;
import com.retailstore.retailstoreassignment.domain.model.entity.Item;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.enums.ItemType;
import com.retailstore.retailstoreassignment.domain.model.enums.UserType;
import com.retailstore.retailstoreassignment.domain.model.exception.BillNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.ItemNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;
import com.retailstore.retailstoreassignment.domain.model.vo.DiscountVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * To run this test from IDE, you should provide environment variables.
 * MONGODB_IP=localhost
 * MONGODB_USER=root
 * MONGODB_PASSWORD=passw0rd
 *
 * You can run test from terminal as below.
 * mvn -DMONGODB_IP=localhost -DMONGODB_USER=root -DMONGODB_PASSWORD=passw0rd test
 */
@SpringBootTest
class BillManagementServiceIntegrationTest {

	@Autowired
	private BillManagementServiceImpl billManagementService;

	@Autowired
	private UserManagementServiceImpl userManagementService;

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	void createAndGetBill() throws UserNotFoundException, BillNotFoundException, DuplicateEmailFoundException {
		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";

		User userRet = User.builder()
				.email(email+1)
				.name(name)
				.lastName(lastname)
				.password(password)
				.build();

		User user = userManagementService.registerUser(userRet);
		Bill bill = Bill.builder()
				.userId(user.getId())
				.itemList(List.of(Item.builder()
								.id("1")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build(),
						Item.builder()
								.id("2")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build(),
						Item.builder()
								.id("3")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build()))
				.build();
		Bill bill1 = billManagementService.createBill(bill);

		Bill bill2 = billManagementService.getBill(bill.getId());

		assertEquals(bill1.getId(), bill2.getId());
		assertEquals(bill1.getUserId(), bill2.getUserId());
		assertEquals(bill1.getItemList().size(), bill2.getItemList().size());
		assertEquals(bill.getItemList().size(), bill2.getItemList().size());
	}

	@AfterEach
	void revertBills(){
		billRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void addItemIntoBill() throws DuplicateEmailFoundException, UserNotFoundException, BillNotFoundException, ItemNotFoundException {
		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";

		User userRet = User.builder()
				.email(email+1)
				.name(name)
				.lastName(lastname)
				.password(password)
				.build();

		User user = userManagementService.registerUser(userRet);
		Bill bill = Bill.builder()
				.userId(user.getId())
				.itemList(List.of(Item.builder()
								.id("1")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build(),
						Item.builder()
								.id("2")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build(),
						Item.builder()
								.id("3")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build()))
				.build();
		billManagementService.createBill(bill);

		Bill billRet = billManagementService.getBill(bill.getId());
		assertEquals(billRet.getItemList().size(), 3);

		Item item = Item.builder()
				.name("item1")
				.itemType(ItemType.MARKET)
				.price(100d)
				.build();

		Bill billDeletedItem = billManagementService.addItemIntoBill(billRet.getId(), item);

		assertEquals(billDeletedItem.getItemList().size(), 4);
	}

	@Test
	void deleteItemFromBill() throws UserNotFoundException, BillNotFoundException, DuplicateEmailFoundException, ItemNotFoundException {
		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";

		User userRet = User.builder()
				.email(email+1)
				.name(name)
				.lastName(lastname)
				.password(password)
				.build();

		User user = userManagementService.registerUser(userRet);
		Bill bill = Bill.builder()
				.userId(user.getId())
				.itemList(List.of(Item.builder()
								.id("1")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build(),
						Item.builder()
								.id("2")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build(),
						Item.builder()
								.id("3")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build()))
				.build();
		billManagementService.createBill(bill);

		Bill billRet = billManagementService.getBill(bill.getId());
		assertEquals(billRet.getItemList().size(), 3);

		String itemId = billRet.getItemList().get(0).getId();

		Bill billDeletedItem = billManagementService.deleteItemFromBill(billRet.getId(), itemId);

		assertEquals(billDeletedItem.getItemList().size(), 2);
	}

	@Test
	void calculateTotalDiscount() throws DuplicateEmailFoundException, UserNotFoundException, BillNotFoundException {

		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";

		User userRet = User.builder()
				.email(email+1)
				.name(name)
				.lastName(lastname)
				.userType(UserType.EMPLOYEE)
				.password(password)
				.build();

		User user = userManagementService.registerUser(userRet);
		Bill bill = Bill.builder()
				.userId(user.getId())
				.itemList(List.of(Item.builder()
								.id("1")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build(),
						Item.builder()
								.id("2")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build(),
						Item.builder()
								.id("3")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build()))
				.build();
		billManagementService.createBill(bill);

		Bill billRet = billManagementService.getBill(bill.getId());

		DiscountVO discountVO = billManagementService.calculateTotalDiscount(billRet.getId());

		assertEquals(discountVO.getDiscount(), 100, 0.1);
		assertEquals(discountVO.getTotalPriceAfterDiscount(), 200, 0.1);
		assertEquals(discountVO.getTotalPrice(), 300, 0.1);
	}

	@Test
	void getTotalPriceAfterDiscount() throws DuplicateEmailFoundException, UserNotFoundException, BillNotFoundException {
		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";

		User userRet = User.builder()
				.email(email+1)
				.name(name)
				.lastName(lastname)
				.userType(UserType.EMPLOYEE)
				.password(password)
				.build();

		User user = userManagementService.registerUser(userRet);
		Bill bill = Bill.builder()
				.userId(user.getId())
				.itemList(List.of(Item.builder()
								.id("1")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build(),
						Item.builder()
								.id("2")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build(),
						Item.builder()
								.id("3")
								.name("item1")
								.itemType(ItemType.MARKET)
								.price(100d)
								.build()))
				.build();
		billManagementService.createBill(bill);

		Bill billRet = billManagementService.getBill(bill.getId());

		Bill b = billManagementService.getTotalPriceAfterDiscount(billRet.getId());

		assertEquals(b.getPrice(), 200, 0.1);
	}

}