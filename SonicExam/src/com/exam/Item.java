package com.exam;

import java.io.Serializable;

/**
 * Represents a part or service that can be sold.
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
public class Item implements  Serializable{
	//Item class must be serializable
	private static final long serialVersionUID = 1L;
	
	private Integer key;
	private String name;
	private float price;

	public Item(Integer key, String name, float price) {
		this.key = key;
		this.name = name;
		this.price = price;
	}

	public Integer getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		Item item = (Item)o;
		
		return key.equals(item.getKey());
	}
}
