package com.kat.myapp.backend.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kat.myapp.backend.exception.ServiceException;

public class Vehicle {
	final static Logger logger = Logger.getLogger(Vehicle.class);
	
	private static final String SQL_SELECT = "select vin,customerID,license,year,color,make,model,mileage from vehicle ";
	private static final String SQL_ORDERBY = " order by license ";
	
	
	private int ownerId;
	private String vin;
	private String licensePlate;
	private String color;

	private String make;
	private String model;
	private String year;
	private int mileage;
	
	
	public Vehicle(ResultSet rs) throws ServiceException {
		
		try {
			setOwnerId(rs.getInt("customerID"));
			setVin(rs.getString("vin"));
			setLicensePlate(rs.getString("license"));
			setYear(rs.getString("year"));
			setColor(rs.getString("color"));
			setModel(rs.getString("model"));
			setMake(rs.getString("make"));
			setMileage(rs.getInt("mileage"));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
		
	}
	
	private static List<Vehicle> retrieveVehiclesByOwmerr(int ownerId) throws ServiceException {
		String sqlWhereString = " Where customerID = " + ownerId;
				
		List<Vehicle> list = new ArrayList<>();
		try {
			Connection connection = DataSourceManager.getInstance().getConnection();
			Statement st = connection.createStatement();
			
			String query_str = SQL_SELECT + sqlWhereString + SQL_ORDERBY;
			logger.debug("query_str: " + query_str);
			ResultSet rs = st.executeQuery(query_str);
			while (rs.next()) {
				Vehicle vehicle = new Vehicle(rs);
				list.add(vehicle);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Retrieve vehicle failed. " + e.getMessage());
		}
		
		return list;
	}
	
	public static List<Vehicle> getVehiclesByOwner(int ownerId) throws ServiceException {
		return retrieveVehiclesByOwmerr(ownerId);
	}
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	public String getVehicleInfo() {
		return getYear() + " " + getMake() + " " + getModel();
	}

	@Override
	public String toString() {
		return "Vehicle [ownerId=" + ownerId + ", vin=" + vin + ", licensePlate=" + licensePlate + ", color=" + color
				+ ", make=" + make + ", model=" + model + ", year=" + year + ", mileage=" + mileage + "]";
	}
	
	

}
