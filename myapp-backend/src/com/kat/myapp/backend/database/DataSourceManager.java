package com.kat.myapp.backend.database;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceManager {

	private static DataSourceManager _instance = new DataSourceManager();
	private BasicDataSource dataSource;

	private DataSourceManager() {
		dataSource = getDataSource();
	}

	public static DataSourceManager getInstance() {
		return _instance;
	}

	private BasicDataSource getDataSource() {

		if (dataSource == null) {
			BasicDataSource ds = new BasicDataSource();
			//ds.setUrl("jdbc:mysql://localhost/myapp");
			ds.setUrl("jdbc:mysql://35.237.136.194:3306/carsystem");
			ds.setUsername("root");
			ds.setPassword("weblogic01");
			ds.setMinIdle(5);
			ds.setMaxIdle(10);
			ds.setMaxOpenPreparedStatements(100);

			dataSource = ds;
		}
		return dataSource;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;

	}

}
