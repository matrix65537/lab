package org.laoguo.chapter1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	private static ThreadLocal<Connection> thl = new ThreadLocal();

	private String url;
	private String user;
	private String password;

	public ConnectionPool(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public Connection getConnection() {
		Connection con = null;

		try {
			con = thl.get();
			if (con == null) {
				con = DriverManager.getConnection(url, user, password);
				thl.set(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
