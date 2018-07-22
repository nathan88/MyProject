package com.kat.myapp.backend.test;

import java.util.List;

import org.apache.log4j.Logger;

import com.kat.myapp.backend.database.Customer;
import com.kat.myapp.backend.exception.ServiceException;



public class CustomerTest {
	final static Logger logger = Logger.getLogger(CustomerTest.class);


	public static void main(String[] args) {
	
		try {
			List<Customer> list = Customer.getCustomers(false);
			for ( Customer cust : list) {
				logger.debug(cust.toString());
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
