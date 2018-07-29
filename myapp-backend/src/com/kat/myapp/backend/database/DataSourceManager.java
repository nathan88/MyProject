package com.kat.myapp.backend.database;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import com.mysql.jdbc.Driver;

public class DataSourceManager {

	final static Logger logger = Logger.getLogger(DataSourceManager.class);
	
	private static DataSourceManager _instance = new DataSourceManager();
	private BasicDataSource dataSource;

	private DataSourceManager() {
		dataSource = getDataSource();
	}

	public static DataSourceManager getInstance() {
		return _instance;
	}

	private BasicDataSource getDataSource() {
		com.mysql.jdbc.Driver driver;
		try {
			driver = new Driver();

		if (dataSource == null) {
			BasicDataSource ds = new BasicDataSource();
			ds.setDriver(driver);
			//ds.setUrl("jdbc:mysql://localhost/myapp");
			String url = "jdbc:mysql://35.237.136.194:3306/carsystem";
			logger.debug("Connect to url: " + url);
			
			ds.setUrl(url);
			ds.setUsername("root");
			ds.setPassword("weblogic01");
			ds.setMinIdle(5);
			ds.setMaxIdle(10);
			ds.setMaxOpenPreparedStatements(100);

			dataSource = ds;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;

	}

}
