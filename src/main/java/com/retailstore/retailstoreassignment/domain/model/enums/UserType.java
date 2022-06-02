package com.retailstore.retailstoreassignment.domain.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum UserType {
	EMPLOYEE(0),
	CUSTOMER(1),
	AFFILIATE(2);

	private static final Map<Integer, UserType> lookup = new HashMap<>();
	@Getter
	private Integer value;
	UserType(int i) {
		value = i;
	}

	static {
		for (UserType e : UserType.values()) {
			lookup.put(e.value, e);
		}
	}

	public static UserType getByValue(Integer i){
		return lookup.get(i);
	}
}
