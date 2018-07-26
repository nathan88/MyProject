package com.kat.myapp.backend.database;

public class Lookup {
	
	private String byName;
	private String byContactNumber;
	private String byPlate;
	
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
	@Override
	public String toString() {
		return "Lookup [byName=" + byName + ", byContactNumber=" + byContactNumber + ", byPlate=" + byPlate + "]";
	}
	
	

}
