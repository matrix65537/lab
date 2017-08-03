package org.laoguo.chapter2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerService {
	public List<Customer> getCustomerList() {
		Connection conn = null;
		try {
			List<Customer> customerList = new ArrayList<Customer>();
			String sql = "select * from customer";
			conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getLong("id"));
				customer.setName(rs.getString("name"));
				customer.setContact(rs.getString("contact"));
				customer.setTelephone(rs.getString("telephone"));
				customer.setEmail(rs.getString("email"));
				customer.setRemark(rs.getString("remark"));
				customerList.add(customer);
			}
			return customerList;
		} catch (SQLException e) {

		} finally {
			DatabaseHelper.closeConnection(conn);
		}
		return null;
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
