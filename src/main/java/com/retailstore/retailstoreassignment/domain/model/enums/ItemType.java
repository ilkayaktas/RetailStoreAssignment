package com.retailstore.retailstoreassignment.domain.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum ItemType {
	MARKET(0),
	TECHNOLOGY(1),
	GROCERIES(2);

	private static final Map<Integer, ItemType> lookup = new HashMap<>();
	@Getter
	private Integer value;
	ItemType(int i) {
		value = i;
	}

	static {
		for (ItemType e : ItemType.values()) {
			lookup.put(e.value, e);
		}
	}

	public static ItemType getByValue(Integer i){
		return lookup.get(i);
	}
}
