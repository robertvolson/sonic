package com.exam;

public interface OrderItem {
	public Item getItem();

	public int getQuantity();

	public boolean getTaxable();
}