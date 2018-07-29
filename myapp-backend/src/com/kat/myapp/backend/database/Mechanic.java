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

public class Mechanic {
	final static Logger logger = Logger.getLogger(Mechanic.class);
	
	private static final String SQL_INSERT = "insert into mechaniclist (mechanicID"
			+ "firstName, middleName, lastName, primaryNumber, secondaryNumber"
			+ ", email, address, city, state, postalCode, country) ";
	private static final String SQL_SELECT = "SELECT mechanicID,firstName,middleName"
			+ ",lastName,primaryNumber,secondaryNumber,email,address,city,"
			+ "state, postalCode, country";
	private static final String SQL_SELECT_ORDER = " Order By mechanicID ";
	
	private int 	mechanicID;
	private int		skillLevel;
	private String 	firstName;
	private String 	middleName;
	private String	lastName;
	private String	primaryNumber;
	private String	secondaryNumber;
	private String 	email;
	private String	address;
	private String	city;
	private String	state;
	private String	postalCode;
	private String 	country;
	
	
	public Mechanic() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Converting values into a SQL statement string for injection
	private String getValuesString() {
		StringBuffer sb = new StringBuffer(" values(");
			
		sb.append(getSkillLevel() + ", ");
		sb.append(StringUtil.addQuotes(getFirstName()) +", ");
		sb.append(StringUtil.addQuotes(getMiddleName()) +", ");
		sb.append(StringUtil.addQuotes(getLastName()) +", ");
		sb.append(StringUtil.addQuotes(getPrimaryNumber()) +", ");
		sb.append(StringUtil.addQuotes(getSecondaryNumber()) +", ");
		sb.append(StringUtil.addQuotes(getEmail()) +", ");
		sb.append(StringUtil.addQuotes(getAddress()) +", ");
		sb.append(StringUtil.addQuotes(getCity()) +", ");
		sb.append(StringUtil.addQuotes(getState()) +", ");
		sb.append(StringUtil.addQuotes(getPostalCode()) +", ");
		sb.append(StringUtil.addQuotes(getCountry()) +", ");
		return sb.toString();
	}
	
	public void addMechanic() throws ServiceException {
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
					setMechanicID(lastid);
					rs.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("Add Mechanic failed. " + e.getMessage());
		}
	}

	public int getMechanicID() {
		return mechanicID;
	}

	public void setMechanicID(int mechanicID) {
		this.mechanicID = mechanicID;
	}
	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrimaryNumber() {
		return primaryNumber;
	}

	public void setPrimaryNumber(String primaryNumber) {
		this.primaryNumber = primaryNumber;
	}

	public String getSecondaryNumber() {
		return secondaryNumber;
	}

	public void setSecondaryNumber(String secondaryNumber) {
		this.secondaryNumber = secondaryNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
