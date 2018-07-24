package com.kat.myapp.backend.test;

import java.util.List;

import org.apache.log4j.Logger;

import com.kat.myapp.backend.database.WorkOrder;
import com.kat.myapp.backend.exception.ServiceException;

public class WorkOrderTest {
	final static Logger logger = Logger.getLogger(WorkOrderTest.class);

	public static void main(String[] args) {
		try {
			// add new workOrder test
	//		WorkOrder workOne = new WorkOrder();
	//		workOne.setAdvisor("Joe Wong");
	//		workOne.setBillingRate(99);
	//		workOne.setCustomerID(1);
	//		workOne.setDateOpened("2018-04-03");
	//		workOne.setEstimateDateTime("2018-04-03");
	//		workOne.setPromisedDateTime("2018-04-03");
	//		workOne.setReadyDateTime("2018-04-03");
	//		workOne.setStatusCode(003);
	//		workOne.setStatusString("Under maintenance");
	//		workOne.setVin("VIN123123123");
	//		workOne.setVisitReason("Totaled");
	//		workOne.setWorkOrderID(003);
		
			List<WorkOrder> list;
		
			logger.debug("====================================================");
			list = WorkOrder.getWorkOrders();
			for ( WorkOrder work : list ) {
				logger.debug(work.toString());
			}
		
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub

	}

}
