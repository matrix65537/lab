package org.laoguo.chapter2;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Worker implements Runnable {
	private static Logger LOGGER = LoggerFactory.getLogger(Worker.class);

	private int id;

	public Worker(int id) {
		this.id = id;
	}

	public void run() {
		CustomerService service = new CustomerService();

		// 添加
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("name", "laoguo");
		fieldMap.put("contact", "ABCD");
		fieldMap.put("telephone", "12345678910");
		fieldMap.put("email", "Mike@gmail.com");
		for (int i = 0; i < 1; i++) {

			service.createCustomer(fieldMap);
			service.updateCustomer(i, fieldMap);
		}

		// 查询所有 List<Customer>
		// list = service.getCustomerList();
		// for (Customer customer : list) {
		// LOGGER.debug(customer.toString());
		// }

		// 查询1个
		Customer customer = service.getCustomer(1);
		// LOGGER.debug(customer.toString());

		String sql = "select * from customer where id > 10 and id < 20";
		List<Map<String, Object>> l = DatabaseHelper.executeQuery(sql, null);
		for (Map<String, Object> x : l) {
			// LOGGER.debug(x.toString());
		}
		LOGGER.error("over" + this.id);
	}

}

public class Test {

	public static void main(String[] args) throws Exception {
		Class.forName("org.laoguo.chapter2.DatabaseHelper");
		int maxThreadCount = 9;
		List<Connection> connList = new ArrayList<Connection>();

		Set<Class<?>> classSet = ClassUtil.getClassSet("org.laoguo.chapter2");
		for (Class<?> cls : classSet) {
			System.out.println(cls);
		}
	}
}
