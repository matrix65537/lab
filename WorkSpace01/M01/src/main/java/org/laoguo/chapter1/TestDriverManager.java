package org.laoguo.chapter1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestDriverManager {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306";
		String user = "laoguo";
		String password = "laoguo";

		Map<Connection, Connection> h = new HashMap();
		Connection con = null;
		int i = 0;
		ConnectionPool pool = new ConnectionPool(url, user, password);
		while (true) {
			
			try {
				con = DriverManager.getConnection(url, user, password);
				//con = pool.getConnection();
			} catch (Exception e) {
				System.out.println(e);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (h.containsKey(con)) {
				// System.out.println("connection in map");
			} else {
				h.put(con, con);
				// con.close();
				// System.out.println("connection not in map");
			}
			i += 1;
			System.out.println(i);
			System.out.println(con.toString());
		}

	}
}
