package com.examtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import com.exam.Item;
import com.exam.Material;
import com.exam.Order;
import com.exam.OrderItem;
import com.exam.Service;

/**
 * 
 * @author Robert Made to test the Order Class
 */
class OrderTest {

	// Static, arbitrary test variables
	private static String[] names = { "Burger", "Tots", "Shake", "Wings", "icee" };
	private static double[] prices = { 5.99, 3.99, 3.12, 6.00, 3.00 };
	private static int[] itemNums = { 3, 2, 1, 1, 6 };

	/**
	 * Static test method used to create filled order object
	 * 
	 * @return order object
	 */
	private static OrderItem[] createOrderItems() {
		OrderItem[] orderItems = new OrderItem[names.length];

		for (int i = 0; i < names.length; i++) {
			OrderItem oi;
			Item item = new Item(i, names[i], (float) prices[i]);
			if (i % 2 == 0) {
				oi = new Material(item, itemNums[i]);
			} else {
				oi = new Service(item, itemNums[i]);
			}
			orderItems[i] = oi;
		}
		return orderItems;
	}

	/**
	 * Tests the getOrderTotal method of an order. It is static because it is used
	 * by two tests
	 * 
	 * @param order is the order object being tested
	 */
	private static void testOrderTotal(Order order) {
		float taxRate = (float) .5;
		float expected = 0;
		for (int i = 0; i < names.length; i++) {
			if (i % 2 == 0) {
				expected += prices[i] * itemNums[i] * (1 + taxRate);
			} else {
				expected += prices[i] * itemNums[i];
			}

		}
		Assert.assertEquals(expected, order.getOrderTotal(taxRate), .005);
	}

	/**
	 * Tests the getItems method of an order. It is static because it is used by two
	 * tests
	 * 
	 * @param order is the order object being tested
	 */
	private static void testNames(Order order) {
		Collection<String> items = order.getItems();
		String[] expectedList = { "Burger", "icee", "Shake", "Tots", "Wings" };
		String[] actualList = items.toArray(new String[names.length]);
		for (int i = 0; i < names.length; i++) {
			Assert.assertEquals(expectedList[i], actualList[i]);
		}
	}

	/**
	 * Tests order total
	 */
	@Test
	void getOrderTotalTest() {
		OrderItem[] orderItems = createOrderItems();
		Order order = new Order(orderItems);
		testOrderTotal(order);
	}

	/**
	 * Tests order names
	 */
	@Test
	void getOrderNamesTest() {
		OrderItem[] orderItems = createOrderItems();
		Order order = new Order(orderItems);
		testNames(order);
	}

	/**
	 * Tests serialization
	 */
	@Test
	void testSerialization() {
		OrderItem[] orderItems = createOrderItems();
		Order order = new Order(orderItems);
		String nameOfFile = "sonicdata.ser";
		try {
			// Serialize Object to file named sonicdata.ser
			FileOutputStream fileOut = new FileOutputStream(nameOfFile);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(order);
			objOut.close();
			fileOut.close();

			// Deserialize object from file
			FileInputStream fileIn = new FileInputStream(nameOfFile);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			order = (Order) objIn.readObject();
			objIn.close();
			fileIn.close();

			// Run tests on order object to make sure serialization worked
			testOrderTotal(order);
			testNames(order);

			// Fail test if any errors are thrown
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.assertEquals(false, true);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertEquals(false, true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Assert.assertEquals(false, true);
		}
	}

	/**
	 * Test using an item as a key for a HashTable
	 */
	@Test
	void testItemHashTable() {
		Hashtable<Item, Integer> table = new Hashtable<Item, Integer>();
		Item[] items = new Item[names.length];

		// Put items into Hashtable
		for (int i = 0; i < names.length; i++) {
			Item item = new Item(i, names[i], (float) prices[i]);
			items[i] = item;
			table.put(item, new Integer(i * 10));
		}

		// Read items out of Hashtable
		for (int i = 0; i < names.length; i++) {
			Assert.assertEquals(new Integer(i * 10), table.get(items[i]));
		}
	}
}