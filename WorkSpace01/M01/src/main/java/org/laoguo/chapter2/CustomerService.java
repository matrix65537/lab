package org.laoguo.chapter2;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class CustomerService {
	public List<Customer> getCustomerList() {
		Connection conn = null;
		try {

			String sql = "select * from customer";
			conn = DatabaseHelper.getConnection();
			return DatabaseHelper.queryEntityList(Customer.class, conn, sql,
					(Object[]) null);
		} finally {
			DatabaseHelper.closeConnection(conn);
		}
	}

	public Customer getCustomer(long id) {
		return null;
	}

	public boolean createCustomer(Map<String, Object> fieldMap) {
		return false;
	}

	public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
		return false;
	}

	public boolean deleteCustomer(long id) {
		return false;
	}
}
