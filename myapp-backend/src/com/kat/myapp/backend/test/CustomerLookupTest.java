package com.kat.myapp.backend.test;

import java.util.List;

import org.apache.log4j.Logger;

import com.kat.myapp.backend.database.Customer;
import com.kat.myapp.backend.database.Lookup;
import com.kat.myapp.backend.database.PhoneType;
import com.kat.myapp.backend.exception.ServiceException;



public class CustomerLookupTest {
	final static Logger logger = Logger.getLogger(CustomerLookupTest.class);


	public static void main(String[] args) {
	
		try {

			
			List<Customer> list;
			Customer customer = new Customer();
			
			Lookup<Customer> lookup = new Lookup<Customer>(customer);
			lookup.setByContactNumber("845");
			logger.debug("====================================================");
			list = lookup.getResult();
			for ( Customer cust : list) {
				logger.debug(cust.toString());
			}
			
			lookup.reset();
			logger.debug("====================================================");
			lookup.setByPlate("G");
			list = lookup.getResult();
			for ( Customer cust : list) {
				logger.debug(cust.toString());
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
