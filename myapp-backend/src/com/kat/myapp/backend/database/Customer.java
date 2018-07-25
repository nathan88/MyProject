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
	
	private static final String SQL_INSERT = "insert into Customer (firstName,lastName,secondaryNumber,primaryNumber,email,address01,address02,city,state,postalCode,country) ";	
	private static final String SQL_SELECT = "Select customerID,firstName,lastName,homeNumber,secondaryNumber,primaryNumber,"
			+ "email,address,city,state,postalCode,country from Customer"; 
	private static final String SQL_WHERE_BY_NAME   = " and firstName like '%@@var%' "; 
	private static final String SQL_WHERE_BY_NUMBER = " and primaryNumber like '%@@var%'  or secondaryNumber like '%@@var%' "; 
//	private static final String SQL_WHERE_BY_PLATE = " firstName like '%@@var%\' "; 
	private static final String SQL_SELECT_ORDER = " Order By firstName, lastName ";
	
	//private static List<Customer>customers ;
	
//	private String sqlWhereString = " Where (1=1) ";  //all
//	private String sqlValueString ;  
	
	private int id;
	private String firstName;
	private String lastName;
	private String secondaryNumber;
	private String primaryNumber ;
	private String email ;
	private String address ;
	private String city ;
	private String state;
	private String postalCode;
	private String country;
	
	
	private String getValuesString() {
		StringBuffer sb = new StringBuffer(" values(");
		
		sb.append(StringUtil.addQuotes(getFirstName()) +", ");
		sb.append(StringUtil.addQuotes(getLastName()) +", ");
		sb.append(StringUtil.addQuotes(getSecondaryNumber()) +", ");
		sb.append(StringUtil.addQuotes(getPrimaryNumber()) +", ");
		sb.append(StringUtil.addQuotes(getEmail()) +", ");
		sb.append(StringUtil.addQuotes(getAddress()) +", ");
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
	
	private static List<Customer> retrieveCustomersByContactNumber(String number) throws ServiceException {
		String sqlWhereString = " Where (1=1) " + SQL_WHERE_BY_NUMBER;
		sqlWhereString = sqlWhereString.replaceAll("@@var", number);
		
		List<Customer> list = new ArrayList<>();
		try {
			Connection connection = DataSourceManager.getInstance().getConnection();
			Statement st = connection.createStatement();
			
			String query_str = SQL_SELECT + sqlWhereString + SQL_SELECT_ORDER;
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
		case PRIAMRY:
			return " Where primaryNumber = " + StringUtil.addQuotes(phoneNumber);
			
		case SECONDARY:
			return " Where secondaryNumber = " + StringUtil.addQuotes(phoneNumber);
			
		
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
			setSecondaryNumber(rs.getString("secondaryNumber"));
			setPrimaryNumber(rs.getString("primaryNumber"));
			setEmail(rs.getString("email"));
			setAddress(rs.getString("address"));
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
	
	public static List<Customer> getCustomersByContactNumber(String number) throws ServiceException {
		return retrieveCustomersByContactNumber(number);
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
	public String getSecondaryNumber() {
		if ( secondaryNumber == null )
			secondaryNumber = "";
		return secondaryNumber;
	}
	public void setSecondaryNumber(String secondaryNumber) {
		this.secondaryNumber = secondaryNumber;
	}
	public String getPrimaryNumber() {
		if ( primaryNumber == null )
			primaryNumber = "";
		return primaryNumber;
	}
	public void setPrimaryNumber(String primaryNumber) {
		this.primaryNumber = primaryNumber;
	}
	public String getEmail() {
		if ( email == null )
			email = "";
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		if ( address == null )
			address = "";
		return address;
	}
	public void setAddress(String address01) {
		this.address = address01;
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
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +  ", secondaryNumber=" 
				+ secondaryNumber + ", primaryNumber=" + primaryNumber + ", email=" + email
				+ ", address01=" + address + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", country=" + country + "]";
	}
	
	

}
