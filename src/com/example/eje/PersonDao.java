package com.example.eje;

import android.database.Cursor;

public interface PersonDao {

	void open();
	void close();
	
	long insertPerson(Person person);
	long updatePerson(Person person);
	long deleteById(Integer id);
	
	Cursor findAll();
	String [] findByName(String name);
	
}
