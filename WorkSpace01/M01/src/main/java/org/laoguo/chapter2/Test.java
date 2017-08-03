package org.laoguo.chapter2;

import java.util.List;

public class Test {

	public static void main(String[] args) throws Exception {
		Class.forName("org.laoguo.chapter2.DatabaseHelper");
		CustomerService service = new CustomerService();
		List<Customer> list = service.getCustomerList();
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}
}
