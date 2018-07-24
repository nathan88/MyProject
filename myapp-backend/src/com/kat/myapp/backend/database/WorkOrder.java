package com.kat.myapp.backend.database;

import org.apache.log4j.Logger;

public class WorkOrder {
	
	final static Logger logger = Logger.getLogger(WorkOrder.class);
	
	private static final String SQL_INSERT = "insert into workOrder (customerID,vin,advisor,dateOpened,visitReason,estimateDateTime,"
			+ "promisedDateTime,statusCode,readyDateTime,billingRate) ";	
	private static final String SQL_SELECT = "Select workOrderID,customerID,vin,advisor,dateOpened,visitReason,estimateDateTime,"  
			+ "promisedDateTime,statusCode,readyDateTime,billingRate, b.description from workOrder a, status b where a.statusCode = b.statusCode "; 
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
	private String statusString;
	


	private Customer customer;
	
	
	
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
	
	public String getStatusString() {
		return statusString;
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
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
	
	//----------------------- Vechile Info --------------------------------------
	
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
				+ ", readyDateTime=" + readyDateTime + ", billingRate=" + billingRate + "]";
	}
	
	

}
