package org.laoguo.chapter2;

import java.util.List;
import java.util.Map;

public class CustomerService {
	public List<Customer> getCustomerList() {
		String sql = "select * from customer";
		return DatabaseHelper.queryEntityList(Customer.class, sql,
				(Object[]) null);
	}

	public Customer getCustomer(long id) {
		String sql = "select * from customer where id = ?";
		return DatabaseHelper.queryEntity(Customer.class, sql, id);
	}

	public boolean createCustomer(Map<String, Object> fieldMap) {
		return DatabaseHelper.insertEntity(Customer.class, fieldMap);
	}

	public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
		return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
	}

	public boolean deleteCustomer(long id) {
		return DatabaseHelper.deleteEntity(Customer.class, id);
	}
}
