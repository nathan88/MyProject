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

public class MechanicWorkOrders {
	final static Logger logger = Logger.getLogger(WorkOrder.class);
	
	private static final String SQL_INSERT = "INSERT INTO mechanicWorkOrders"
			+ " (mechanicID, workOrderID) ";
	private static final String SQL_SELECT = "SELECT mechanicID, workOrderID ";
	private static final String SQL_SELECT_ORDER = " Order by mechanicID ";
	
	private int mechanicID;
	private int workOrderID;
	
	public MechanicWorkOrders() {
		super();
	}

	public int getMechanicID() {
		return mechanicID;
	}

	public void setMechanicID(int mechanicID) {
		this.mechanicID = mechanicID;
	}

	public int getWorkOrderID() {
		return workOrderID;
	}

	public void setWorkOrderID(int workOrderID) {
		this.workOrderID = workOrderID;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static String getSqlInsert() {
		return SQL_INSERT;
	}

	public static String getSqlSelect() {
		return SQL_SELECT;
	}

	public static String getSqlSelectOrder() {
		return SQL_SELECT_ORDER;
	}
	

	
}
