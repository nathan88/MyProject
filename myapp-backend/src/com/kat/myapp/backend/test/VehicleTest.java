package com.kat.myapp.backend.test;

import java.util.List;

import org.apache.log4j.Logger;

import com.kat.myapp.backend.database.Customer;
import com.kat.myapp.backend.database.Vehicle;
import com.kat.myapp.backend.exception.ServiceException;



public class VehicleTest {
	final static Logger logger = Logger.getLogger(VehicleTest.class);


	public static void main(String[] args) {
	
		try {
			
			List<Customer> list;
			
			
			
			logger.debug("====================================================");
			list = Customer.getCustomers();
			for ( Customer cust : list) {
				logger.debug(cust.toString());
				
				logger.debug(Vehicle.getVehiclesByOwner(cust.getId()) );
			}
			

				
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
