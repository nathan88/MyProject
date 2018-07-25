package com.kat.myapp.backend.database;

public enum StatusType {
	QUEUED(1),
	DIAGNOSIS(2),
	IN_SHOP(3),
	IN_PROGRESS_I(4),
	IN_PROGRESS_II(5),
	IN_PROGRESS_III(6),
	CAR_WASH(7);
	
	int value;
	StatusType (int value) {
		this.value = value;
	}
	
	public int toValue() {
		return value;
	}
	
	public String toString() {
		return value+"";
	}
	
}
