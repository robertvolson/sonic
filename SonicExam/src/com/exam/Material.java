package com.exam;

import java.io.Serializable;

public class Material implements OrderItem, Serializable {
	
	//required to be serializable
	private static final long serialVersionUID = 1L;
	
	private final Item item;
	private final int quantity;
	//Material items are always taxable
	private static final boolean taxable = true;
	
	public Material(Item item, int quantity) {
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
