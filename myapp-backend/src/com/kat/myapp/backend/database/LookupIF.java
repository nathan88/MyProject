package com.kat.myapp.backend.database;

import java.util.List;

import com.kat.myapp.backend.exception.ServiceException;

public interface LookupIF<T> {
	
	public List<T> getResult(String value, LookupType type) throws ServiceException;

}
