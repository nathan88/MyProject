package com.kat.myapp.backend.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kat.myapp.backend.exception.ServiceException;

public class Customer {
	final static Logger logger = Logger.getLogger(Customer.class);
	
	private static final String SQL_SELECT = "Select customerID,firstName,lastName,homeNumber,workNumber,cellNumber,"
			+ "email,address01,address02,city,state,postalCode,country from Customer"; 
	private static final String SQL_SELECT_ORDER = " Order By firstName, lastName ";
	
	private static List<Customer>customers ;
	
	private String sqlWhereString = " Where (1=1) ";  //all
	
	private int id;
	private String firstName;
	private String lastName;
	private String homeNumber;
	private String workNumber;
	private String cellNumber;
	private String email;
	private String address01;
	private String address02; 
	private String city;
	private String state;
	private String postalCode;
	private String country;
	
	
	private static void retrieveCustomers() throws ServiceException {
		try {
			Connection connection = DataSourceManager.getInstance().getConnection();
			Statement st = connection.createStatement();
			
			String query_str = SQL_SELECT + SQL_SELECT_ORDER;
			logger.debug("query_str: " + query_str);
			ResultSet rs = st.executeQuery(query_str);
			while (rs.next()) {
				Customer cust = new Customer(rs);
				customers.add(cust);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Retrieve Customers failed. " + e.getMessage());
		}
	}
	
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(ResultSet rs) throws ServiceException {
		
		try {
			setId(rs.getInt("customerID"));
			setFirstName(rs.getString("firstName"));
			setLastName(rs.getString("lastName"));
			setHomeNumber(rs.getString("homeNumber"));
			setWorkNumber(rs.getString("workNumber"));
			setCellNumber(rs.getString("cellNumber"));
			setEmail(rs.getString("email"));
			setAddress01(rs.getString("address01"));
			setAddress02(rs.getString("address02"));
			setCity(rs.getString("city"));
			setState(rs.getString("state"));
			setPostalCode(rs.getString("postalCode"));
			setCountry(rs.getString("country"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
		
	}

	public static List<Customer> getCustomers(boolean refresh) throws ServiceException {
		if ( customers == null || refresh ) {
			customers = new ArrayList<>();
			retrieveCustomers();
		}
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public String getSqlWhereString() {
		return sqlWhereString;
	}
	public void setSqlWhereString(String sqlWhereString) {
		this.sqlWhereString = sqlWhereString;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getHomeNumber() {
		return homeNumber;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	public String getWorkNumber() {
		return workNumber;
	}
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}
	public String getCellNumber() {
		return cellNumber;
	}
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress01() {
		return address01;
	}
	public void setAddress01(String address01) {
		this.address01 = address01;
	}
	public String getAddress02() {
		return address02;
	}
	public void setAddress02(String address02) {
		this.address02 = address02;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}



	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", homeNumber="
				+ homeNumber + ", workNumber=" + workNumber + ", cellNumber=" + cellNumber + ", email=" + email
				+ ", address01=" + address01 + ", address02=" + address02 + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", country=" + country + "]";
	}
	
	

}
