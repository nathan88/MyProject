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

public class Customer {
	final static Logger logger = Logger.getLogger(Customer.class);
	
	private static final String SQL_INSERT = "insert into Customer (firstName,lastName,homeNumber,workNumber,cellNumber,email,address01,address02,city,state,postalCode,country) ";	
	private static final String SQL_SELECT = "Select customerID,firstName,lastName,homeNumber,workNumber,cellNumber,"
			+ "email,address01,address02,city,state,postalCode,country from Customer"; 
	private static final String SQL_SELECT_ORDER = " Order By firstName, lastName ";
	
	//private static List<Customer>customers ;
	
	private String sqlWhereString = " Where (1=1) ";  //all
//	private String sqlValueString ;  
	
	private int id;
	private String firstName;
	private String lastName;
	private String homeNumber;
	private String workNumber;
	private String cellNumber ;
	private String email ;
	private String address01 ;
	private String address02 ; 
	private String city ;
	private String state;
	private String postalCode;
	private String country;
	
	
	private String getValuesString() {
		StringBuffer sb = new StringBuffer(" values(");
		
		sb.append(StringUtil.addQuotes(getFirstName()) +", ");
		sb.append(StringUtil.addQuotes(getLastName()) +", ");
		sb.append(StringUtil.addQuotes(getHomeNumber()) +", ");
		sb.append(StringUtil.addQuotes(getWorkNumber()) +", ");
		sb.append(StringUtil.addQuotes(getCellNumber()) +", ");
		sb.append(StringUtil.addQuotes(getEmail()) +", ");
		sb.append(StringUtil.addQuotes(getAddress01()) +", ");
		sb.append(StringUtil.addQuotes(getAddress02()) +", ");
		sb.append(StringUtil.addQuotes(getCity()) +", ");
		sb.append(StringUtil.addQuotes(getState()) +", ");
		sb.append(StringUtil.addQuotes(getPostalCode()) +", ");
		sb.append(StringUtil.addQuotes(getCountry()) +") ");
		
		return sb.toString();
	}
	public  void addCustomer() throws ServiceException {
		try {
			Connection connection = DataSourceManager.getInstance().getConnection();
			Statement st = connection.createStatement();
			
			String insert_str = SQL_INSERT + getValuesString();
			logger.debug("query_str: " + insert_str);
			int cnt = st.executeUpdate(insert_str);
			 
			if ( cnt > 0 ) {
				ResultSet rs = st.executeQuery("select last_insert_id()");
				if ( rs.next() ) {
					int lastid = rs.getInt(1);
					logger.debug("last_id: <" + lastid + ">");
					setId(lastid);
					rs.close();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Retrieve Customers failed. " + e.getMessage());
		}
	}
	
	private static List<Customer> retrieveCustomers() throws ServiceException {
		List<Customer> list = new ArrayList<>();
		try {
			Connection connection = DataSourceManager.getInstance().getConnection();
			Statement st = connection.createStatement();
			
			String query_str = SQL_SELECT + SQL_SELECT_ORDER;
			logger.debug("query_str: " + query_str);
			ResultSet rs = st.executeQuery(query_str);
			while (rs.next()) {
				Customer cust = new Customer(rs);
				list.add(cust);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Retrieve Customers failed. " + e.getMessage());
		}
		
		return list;
	}
	
	private static List<Customer> retrieveCustomerByPhone(String phoneNumber, PhoneType type) throws ServiceException {
		List<Customer> list = new ArrayList<>();
		try {
			Connection connection = DataSourceManager.getInstance().getConnection();
			Statement st = connection.createStatement();
			
			String query_str = SQL_SELECT + getPhoneValueString(phoneNumber, type) + SQL_SELECT_ORDER;
			logger.debug("query_str: " + query_str);
			ResultSet rs = st.executeQuery(query_str);
			while (rs.next()) {
				Customer cust = new Customer(rs);
				list.add(cust);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Retrieve Customers failed. " + e.getMessage());
		}
		
		return list;
	}
	
	private static String getPhoneValueString(String phoneNumber, PhoneType type) {
		switch ( type ) {
		case HOME_PHONE:
			return " Where homeNumber = " + StringUtil.addQuotes(phoneNumber);
			
		case WORK_PHONE:
			return " Where workNumber = " + StringUtil.addQuotes(phoneNumber);
			
		case CELL_PHONE:
			return " Where cellNumber = " + StringUtil.addQuotes(phoneNumber);
			
		}
		
		return "";
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

	public static List<Customer> getCustomers() throws ServiceException {
		return retrieveCustomers();
	}
	
	public static List<Customer> getCustomersByPhone(String phoneNumber, PhoneType type) throws ServiceException {
		return retrieveCustomerByPhone(phoneNumber, type);
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
		if ( firstName == null )
			firstName = "";
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		if ( lastName == null )
			lastName = "";
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getHomeNumber() {
		if ( homeNumber == null )
			homeNumber = "";
		return homeNumber;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	public String getWorkNumber() {
		if ( workNumber == null )
			workNumber = "";
		return workNumber;
	}
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}
	public String getCellNumber() {
		if ( cellNumber == null )
			cellNumber = "";
		return cellNumber;
	}
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public String getEmail() {
		if ( email == null )
			email = "";
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress01() {
		if ( address01 == null )
			address01 = "";
		return address01;
	}
	public void setAddress01(String address01) {
		this.address01 = address01;
	}
	public String getAddress02() {
		if ( address02 == null )
			address02 = "";
		return address02;
	}
	public void setAddress02(String address02) {
		this.address02 = address02;
	}
	public String getCity() {
		if ( city == null )
			city = "";
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		if ( state == null )
			state = "";
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		if ( postalCode == null )
			postalCode = "";
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		if ( country == null )
			country = "";
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
