package org.laoguo.chapter1;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCP {

	private String url = "jdbc:mysql://localhost:3306";
	private String user = "laoguo";
	private String password = "laoguo";
	private String classDriver = "com.mysql.jdbc.Driver";

	public void test() {
		BasicDataSource ds = new BasicDataSource();

		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setDriverClassName(classDriver);

		ds.setInitialSize(5);
		ds.setMaxTotal(10);
		ds.setMaxWaitMillis(2000);

		for (int i = 0; i < 11; ++i) {
			Connection con = null;
			try {
				con = ds.getConnection();
				System.out.println("" + i + " : " + con.hashCode());
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new DBCP().test();
	}

}
