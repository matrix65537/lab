package org.laoguo.chapter1;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

class A{
	static{
		System.out.println("load class A");
	}
}

public class ClassLoad {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println(driver);
			String url = "jdbc:mysql://127.0.0.1:8888";
			Properties prop = new Properties();
			prop.put("user", "laoguo");
			prop.put("password", "laoguo");
			driver.connect(url, prop);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
