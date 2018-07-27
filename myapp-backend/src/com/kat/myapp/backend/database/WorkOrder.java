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


public class WorkOrder {
	final static Logger logger = Logger.getLogger(WorkOrder.class);
	
	private static final String SQL_INSERT = "INSERT INTO workOrder (customerID"
			+ ",vin,advisor,dateOpened,visitReason,estimateDateTime,"
			+ "promisedDateTime,statusCode,readyDateTime,billingRate) ";
	private static final String SQL_SELECT = "SELECT workOrderID, customerID, "
			+ "vin, advisor, dateOpened, visitReason, estimateDateTime,"  
			+ "promisedDateTime, a.statusCode, readyDateTime, billingRate, "
			+ "b.description as statusDescription from workOrder a, status "
			+ "b where a.statusCode = b.statusCode ";
	//private static final String SQL_SELECT = "SELECT workOrderID, customerID, vin, advisor, dateOpened, visitReason, estimateDateTime,"
	//		+ "promisedDateTime, statusCode, readyDateTime, billingRate from workOrder";
	private static final String SQL_SELECT_ORDER = " Order By dateOpened ";
	
	private int workOrderID;
	private int customerID	;
	private String vin	;
	private String advisor;
	private String dateOpened	;
	private String visitReason;
	private String estimateDateTime;
	private String promisedDateTime	;
	private int statusCode	;
	private String readyDateTime;
	private float billingRate;
	private String statusDescription;
	
	private Customer customer;
	
	// Converting values into a SQL statement string for injection
	private String getValuesString() {
		StringBuffer sb = new StringBuffer(" values(");
		
		sb.append(getCustomerID() +", ");
		sb.append(StringUtil.addQuotes(getVin()) +", ");
		sb.append(StringUtil.addQuotes(getAdvisor()) +", ");
		sb.append(StringUtil.addQuotes(getDateOpened()) +", ");
		sb.append(StringUtil.addQuotes(getVisitReason()) +", ");
		sb.append(StringUtil.addQuotes(getEstimateDateTime()) +", ");
		sb.append(StringUtil.addQuotes(getPromisedDateTime()) +", ");
		sb.append(getStatusCode() +", ");
		sb.append(StringUtil.addQuotes(getReadyDateTime()) +", ");
		sb.append(getBillingRate() +") ");
		
		return sb.toString();
	}
	
	// Tries to add new workOrder into database and increments the primary key (workOrde
	public void addWorkOrder() throws ServiceException {
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
					setWorkOrderID(lastid);
					rs.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("Add WorkOrders failed. " + e.getMessage());
		}
	}
	
	private static List<WorkOrder> retrieveWorkOrders() throws ServiceException {
		List<WorkOrder> list = new ArrayList<>();
		try {
			Connection connection = DataSourceManager.getInstance().getConnection();
			Statement st = connection.createStatement();
			String query_str = SQL_SELECT + SQL_SELECT_ORDER;
			logger.debug("query_str: " + query_str);
			ResultSet rs = st.executeQuery(query_str);
			while (rs.next()) {
				WorkOrder work = new WorkOrder(rs);
				list.add(work);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("Retrieve WorkOrders failed. " + e.getMessage());
		}
		return list;
	}

	public WorkOrder() {
		super();
	}
	
	public WorkOrder(ResultSet rs) throws ServiceException {
		try {
			setWorkOrderID(rs.getInt("workOrderID"));
			setCustomerID(rs.getInt("customerID"));
			setVin(rs.getString("vin"));
			setAdvisor(rs.getString("advisor"));
			setDateOpened(rs.getString("dateOpened"));
			setVisitReason(rs.getString("visitReason"));
			setEstimateDateTime(rs.getString("estimateDateTime"));
			setPromisedDateTime(rs.getString("promisedDateTime"));
			setStatusCode(rs.getInt("statusCode"));
			setReadyDateTime(rs.getString("readyDateTime"));
			setBillingRate(rs.getFloat("billingRate"));
			setStatusDescription(rs.getString("statusDescription"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public static List<WorkOrder> getWorkOrders() throws ServiceException {
		return retrieveWorkOrders();
	}
	
	public void addMechanic(int mechanicID) {
		MechanicWorkOrderList newWorkOrder = new MechanicWorkOrderList();
		newWorkOrder.setMechanicID(mechanicID);
		newWorkOrder.setWorkOrderID(getWorkOrderID());
	}
	
	public static void addTask(int taskID) {
		return;
	}
	
	
	
	public int getWorkOrderID() {
		return workOrderID;
	}
	public void setWorkOrderID(int workOrderID) {
		this.workOrderID = workOrderID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getAdvisor() {
		return advisor;
	}
	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}
	public String getDateOpened() {
		return dateOpened;
	}
	public void setDateOpened(String dateOpened) {
		this.dateOpened = dateOpened;
	}
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	public String getEstimateDateTime() {
		return estimateDateTime;
	}
	public void setEstimateDateTime(String estimateDateTime) {
		this.estimateDateTime = estimateDateTime;
	}
	public String getPromisedDateTime() {
		return promisedDateTime;
	}
	public void setPromisedDateTime(String promisedDateTime) {
		this.promisedDateTime = promisedDateTime;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getReadyDateTime() {
		return readyDateTime;
	}
	public void setReadyDateTime(String readyDateTime) {
		this.readyDateTime = readyDateTime;
	}
	public float getBillingRate() {
		return billingRate;
	}
	public void setBillingRate(float billingRate) {
		this.billingRate = billingRate;
	}
	
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	//---------------------  Customer Info ----------------------------------------
	
	public String getCustomerName() {
		return customer.getFirstName() + " " + customer.getLastName();
	}
	
	public String getCustomnerContactNumber () {
		if ( customer.getPrimaryNumber().length() > 0 )
			return customer.getPrimaryNumber();
		else 
			return customer.getSecondaryNumber();
	}
	

	//----------------------- Vehicle Info --------------------------------------
	
	public String getVehicleMake() {
		return "";
	}
	
	public String getVehicleModel() {
		return "";
	}
	
	public String getVehicleYear() {
		return "";
	}
	
	public String getVehicleColor() {
		return "";
	}
	
	
	
	@Override
	public String toString() {
		return "WorkOrder [workOrderID=" + workOrderID + ", customerID=" + customerID + ", vin=" + vin + ", advisor="
				+ advisor + ", dateOpened=" + dateOpened + ", visitReason=" + visitReason + ", estimateDateTime="
				+ estimateDateTime + ", promisedDateTime=" + promisedDateTime + ", statusCode=" + statusCode
				+ ", readyDateTime=" + readyDateTime + ", billingRate=" + billingRate + ", statusDescription=" + statusDescription + "]";
	}
	
	

}
