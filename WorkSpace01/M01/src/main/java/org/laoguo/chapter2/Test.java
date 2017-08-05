package org.laoguo.chapter2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

	private static Logger LOGGER = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws Exception {
		Class.forName("org.laoguo.chapter2.DatabaseHelper");
		CustomerService service = new CustomerService();

		// 添加
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("name", "laoguo");
		fieldMap.put("contact", "ABCD");
		fieldMap.put("telephone", "12345678910");
		fieldMap.put("email", "Mike@gmail.com");
		for (int i = 0; i < 10; i++) {

			service.createCustomer(fieldMap);
			service.updateCustomer(i, fieldMap);
		}

		// 查询所有
		List<Customer> list = service.getCustomerList();
		for (Customer customer : list) {
			//LOGGER.debug(customer.toString());
		}

		// 查询1个
		Customer customer = service.getCustomer(1);
		LOGGER.debug(customer.toString());
	}
}
