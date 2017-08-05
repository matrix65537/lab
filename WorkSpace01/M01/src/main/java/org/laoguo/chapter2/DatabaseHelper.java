package org.laoguo.chapter2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DatabaseHelper {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DatabaseHelper.class);

	private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();
	private static final QueryRunner QUERY_RUNNER = new QueryRunner();

	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;

	// 加载mysql驱动
	static {
		Properties conf = PropsUtil.loadProps("config.properties");
		DRIVER = conf.getProperty("jdbc.driver");
		URL = conf.getProperty("jdbc.url");
		USERNAME = conf.getProperty("jdbc.username");
		PASSWORD = conf.getProperty("jdbc.password");

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.error("can not load jdbc driver", e);
		}
	}

	// 获取数据库连接
	public static Connection getConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				CONNECTION_HOLDER.set(conn);
				LOGGER.info("get connection");
			} catch (SQLException e) {
				LOGGER.error("get connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
		return conn;
	}

	// 关闭数据库连接
	public static void closeConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}

	// 查询对象列表
	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql,
			Object... params) {
		List<T> entityList;
		try {
			Connection conn = getConnection();
			entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(
					entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		return entityList;
	}

	// 查询对象
	public static <T> T queryEntity(Class<T> entityClass, String sql,
			Object... params) {
		T entity;
		try {
			Connection conn = getConnection();
			LOGGER.debug(sql);
			entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(
					entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		return entity;
	}

	// 查询对象Map
	public static List<Map<String, Object>> executeQuery(String sql,
			Object... params) {
		List<Map<String, Object>> result;
		try {
			Connection conn = getConnection();
			result = QUERY_RUNNER
					.query(conn, sql, new MapListHandler(), params);
		} catch (SQLException e) {
			LOGGER.error("execute query failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		return result;
	}

	// 执行修改（插入，更新，删除）
	public static int executeUpdate(String sql, Object... params) {
		int rows = 0;
		try {
			Connection conn = getConnection();
			LOGGER.debug(sql);
			rows = QUERY_RUNNER.update(conn, sql, params);
		} catch (SQLException e) {
			LOGGER.error("execute update failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		return rows;
	}

	public static String getTableName(Class<?> entityClass) {
		return entityClass.getSimpleName();
	}

	public static <T> boolean insertEntity(Class<T> entityClass,
			Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not insert entity: fieldMap is empty");
			return false;
		}
		String sql = "insert into " + getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		sql += columns + " values" + values;

		Object[] params = fieldMap.values().toArray();
		LOGGER.debug(sql);
		int rows = executeUpdate(sql, params);
		return rows == 1;
	}

	public static <T> boolean updateEntity(Class<T> entityClass, long id,
			Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not update entity: fieldMap is empty");
			return false;
		}
		String sql = "update " + getTableName(entityClass) + " set ";
		StringBuilder columns = new StringBuilder("");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append("=?, ");
		}
		sql += columns.substring(0, columns.lastIndexOf(", ")) + " where id = ?";

		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();
		LOGGER.debug(sql);
		int rows = executeUpdate(sql, params);
		return rows == 1;
	}

	public static <T> boolean deleteEntity(Class<T> entityClass, long id) {

		String sql = "delete from " + getTableName(entityClass)
				+ " where id = ?";
		LOGGER.debug(sql);
		int rows = executeUpdate(sql, id);
		return rows == 1;
	}
}
