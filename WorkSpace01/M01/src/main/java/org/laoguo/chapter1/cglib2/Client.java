package org.laoguo.chapter1.cglib2;

public class Client {

	public static void doMethod(TableDAO dao) {
		dao.create();
		dao.query();
		dao.update();
		dao.delete();
	}

	public static void haveAuth() {
		TableDAO tableDao = TableDAOFactory.getAuthInstance(new AuthProxy(
				"ABCD"));
		doMethod(tableDao);
	}

	public static void haveNoAuth() {
		TableDAO tableDao = TableDAOFactory
				.getAuthInstance(new AuthProxy("ABC"));
		doMethod(tableDao);
	}

	public static void haveAuthByFilter() {
		TableDAO tableDao;
		tableDao = TableDAOFactory
				.getAuthInstanceByFilter(new AuthProxy("ABCD"));
		doMethod(tableDao);

		//tableDao = TableDAOFactory.getAuthInstanceByFilter(new AuthProxy("ABC"));
		//doMethod(tableDao);
	}

	public static void main(String[] args) {
		//TableDAO tableDao = TableDAOFactory.getInstance();
		//doMethod(tableDao);

		//haveAuth();
		//haveNoAuth();
		haveAuthByFilter();
	}
}
