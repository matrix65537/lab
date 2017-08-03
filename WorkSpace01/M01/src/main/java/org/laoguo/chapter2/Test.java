package org.laoguo.chapter2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

	private static Logger LOGGER = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws Exception {
		Class.forName("org.laoguo.chapter2.DatabaseHelper");
		CustomerService service = new CustomerService();
		List<Customer> list = service.getCustomerList();
		for (Customer customer : list) {
			LOGGER.debug(customer.toString());
		}
	}
}
