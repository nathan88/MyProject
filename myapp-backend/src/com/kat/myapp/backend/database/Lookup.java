package com.kat.myapp.backend.database;

import java.io.Serializable;
import java.util.List;

import com.kat.myapp.backend.exception.ServiceException;

public class Lookup<T> implements Serializable {
	
	LookupIF result;
	
	public Lookup(LookupIF result) {
		super();
		this.result = result;
	}

	private String byName = "";
	private String byContactNumber = "";
	private String byPlate = "";
	
	

	public String getByName() {
		return byName;
	}
	public void setByName(String byName) {
		this.byName = byName;
	}
	public String getByContactNumber() {
		return byContactNumber;
	}
	public void setByContactNumber(String byContactNumber) {
		this.byContactNumber = byContactNumber;
	}
	public String getByPlate() {
		return byPlate;
	}
	public void setByPlate(String byPlate) {
		this.byPlate = byPlate;
	}
	
	public void reset() {
		byName = "";
		byContactNumber = "";
		byPlate = "";
	}
	@Override
	public String toString() {
		return "Lookup [byName=" + byName + ", byContactNumber=" + byContactNumber + ", byPlate=" + byPlate + "]";
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getResult() throws ServiceException {
		String value = null;
		LookupType type = null;
		
		if ( byName.trim().length() > 0 ) {
			value = byName;
			type = LookupType.BYNAME;
		} else
			if ( byContactNumber.trim().length() > 0 ) {
				value = byContactNumber;
				type = LookupType.BYCONTACT;
		} else {
			if ( byPlate.trim().length() > 0 ) {
				value = byPlate;
				type = LookupType.BYPLATE;
			}	
		}
		
		return result.getResult(value, type);
	}
	
	

}
