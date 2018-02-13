package com.exam;

import java.io.Serializable;

public class Service implements OrderItem, Serializable {
	
	//required to be serializable
	private static final long serialVersionUID = 1L;
	
	private final Item item;
	private final int quantity;
	//Service items are never taxable
	private static final boolean taxable = false;
	
	public Service(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	@Override
	public Item getItem() {
		return item;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public boolean getTaxable() {
		return taxable;
	}

}
