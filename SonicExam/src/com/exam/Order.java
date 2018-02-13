package com.exam;

import java.io.Serializable;
import java.util.*;

/**
 * Represents and Order that contains a collection of items.
 *
 * care should be taken to ensure that this class is immutable since it is sent
 * to other systems for processing that should not be able to change it in any
 * way.
 *
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Exams are us
 * </p>
 * 
 * @author Joe Blow
 * @version 1.0
 */
public class Order implements Serializable {
	// Order class must be serializable
	private static final long serialVersionUID = 1L;

	private OrderItem[] orderItems;

	public Order(OrderItem[] orderItems) {
		this.orderItems = orderItems;
	}

	// Returns the total order cost after the tax has been applied
	public float getOrderTotal(float taxRate) {
		float orderTotal = 0;
		for (OrderItem orderItem : orderItems) {
			// Price of single item
			float price = orderItem.getItem().getPrice();
			int numberItems = orderItem.getQuantity();
			// Add single item price to total
			orderTotal += price * numberItems;

			// Check to see if order item is taxable
			if (orderItem.isTaxable()) {
				// if order item is taxable, add tax rate to total
				orderTotal += price * taxRate * numberItems;
			}
		}
		// return tax rounded to nearest penny
		return round(orderTotal);
	}

	private float round(float num) {
		// Multiply num by 100
		num = num * 100;

		// Round num to nearest 1
		int roundedNumInt = Math.round(num);

		// Divide back to float and return
		return ((float) (roundedNumInt)) / 100;
	}

	/**
	 * Returns a Collection of all items sorted by item name (case-insensitive).
	 *
	 * @return Collection
	 */
	public Collection<String> getItems() {
		ArrayList<String> itemNames = new ArrayList<String>();

		// Iterate through each orderItem, and add the item name to a list
		for (OrderItem orderItem : orderItems) {
			String itemName = orderItem.getItem().getName();
			itemNames.add(itemName);
		}
		// Sort, ignoring case
		itemNames.sort(String::compareToIgnoreCase);
		return itemNames;
	}
}