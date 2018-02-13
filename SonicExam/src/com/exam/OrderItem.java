package com.exam;

public interface OrderItem {
	/**
	 * @return Item associated with order
	 */
	public Item getItem();

	/**
	 * @return Number of items in the order
	 */
	public int getQuantity();

	/**
	 * @return Whether or not the item is taxable
	 */
	public boolean isTaxable();
}