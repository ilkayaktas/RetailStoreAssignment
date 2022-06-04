package com.retailstore.retailstoreassignment.domain.service;

import com.retailstore.retailstoreassignment.domain.model.entity.Item;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.enums.ItemType;
import com.retailstore.retailstoreassignment.domain.model.enums.UserType;
import com.retailstore.retailstoreassignment.domain.model.vo.DiscountVO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
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
class BillManagementServiceUnitTest {

	@InjectMocks
	private BillManagementServiceImpl billManagementService;


	@Test
	void calculateDiscount() {
		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";
		String passwordEncoded = "pasw0rd123-encoded";

		List<Item> items = List.of(Item.builder()
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
						.build());

		User user = User.builder()
				.email(email)
				.name(name)
				.lastName(lastname)
				.password(password)
				.createdAt(LocalDateTime.now())
				.userType(UserType.EMPLOYEE)
				.build();

		DiscountVO discountVO = billManagementService.calculateDiscount(items, user);

		// %30 discount from being Employee. 210 remains. 2x5=10 additional discount (for each 100$). 200 remains
		assertEquals(discountVO.getDiscount(), 100, 0.1);
		assertEquals(discountVO.getTotalPriceAfterDiscount(), 200, 0.1);
		assertEquals(discountVO.getTotalPrice(), 300, 0.1);


		user.setUserType(UserType.AFFILIATE);
		discountVO = billManagementService.calculateDiscount(items, user);
		// %10 discount from being Affiliate. 270 remains. 2x5=10 additional discount (for each 100$). 260 remains
		assertEquals(discountVO.getDiscount(), 40, 0.1);
		assertEquals(discountVO.getTotalPriceAfterDiscount(), 260, 0.1);
		assertEquals(discountVO.getTotalPrice(), 300, 0.1);

		user.setUserType(UserType.CUSTOMER);
		discountVO = billManagementService.calculateDiscount(items, user);
		// No discount 3x5=15 additional discount (for each 100$). 285 remains
		assertEquals(discountVO.getDiscount(), 15, 0.1);
		assertEquals(discountVO.getTotalPriceAfterDiscount(), 285, 0.1);
		assertEquals(discountVO.getTotalPrice(), 300, 0.1);

		user.setUserType(UserType.CUSTOMER);
		user.setCreatedAt(LocalDateTime.now().minusYears(10));
		discountVO = billManagementService.calculateDiscount(items, user);
		// %5 discount because 10years customer, 285 remains. 2x5=10 additional discount (for each 100$). 275 remains
		assertEquals(discountVO.getDiscount(), 25, 0.1);
		assertEquals(discountVO.getTotalPriceAfterDiscount(), 275, 0.1);
		assertEquals(discountVO.getTotalPrice(), 300, 0.1);

	}

	@Test
	void calculatediscountForRepetetiveAmount() {
		Double ret = billManagementService.calculatediscountForRepetetiveAmount(550d);

		assertEquals(ret, 25,0.1);

		ret = billManagementService.calculatediscountForRepetetiveAmount(990d);

		assertEquals(ret, 45,0.1);
	}

	@Test
	void findYearDifferenceFromNow() {

		Long yearDifferenceFromNow = billManagementService.findYearDifferenceFromNow(LocalDateTime.now().minusYears(3));
		assertEquals(yearDifferenceFromNow, 3);

		yearDifferenceFromNow = billManagementService.findYearDifferenceFromNow(LocalDateTime.now().minusYears(1));
		assertEquals(yearDifferenceFromNow, 1);
	}
}