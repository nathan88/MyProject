package com.kat.myapp.backend.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kat.myapp.backend.exception.ServiceException;
import com.kat.myapp.backend.util.StringUtil;

public class MechanicList {
	final static Logger logger = Logger.getLogger(MechanicList.class);
	
	private static final String SQL_INSERT = "insert into mechaniclist (mechanicID"
			+ "firstName, middleName, lastName, primaryNumber, secondaryNumber"
			+ ", email, address, city, state, postalCode, country) ";
	private static final String SQL_SELECT = "SELECT 
	private static final String SQL_SELECT_ORDER = " Order By dateOpened ";
}
