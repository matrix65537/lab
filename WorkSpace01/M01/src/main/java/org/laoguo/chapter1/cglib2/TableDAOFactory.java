package org.laoguo.chapter1.cglib2;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class TableDAOFactory {
	private static TableDAO tDao = new TableDAO();

	public static TableDAO getInstance() {
		return tDao;
	}

	public static TableDAO getAuthInstance(AuthProxy authProxy) {
		Enhancer en = new Enhancer();
		en.setSuperclass(TableDAO.class);
		en.setCallback(authProxy);
		return (TableDAO) en.create();
	}

	public static TableDAO getAuthInstanceByFilter(AuthProxy authProxy) {
		Enhancer en = new Enhancer();
		en.setSuperclass(TableDAO.class);

		en.setCallbackFilter(new AuthProxyFilter());
		en.setCallbacks(new Callback[]{authProxy, NoOp.INSTANCE});
		return (TableDAO) en.create();
	}
}
