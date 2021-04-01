package com.example.eje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonDaoImpl extends SQLiteOpenHelper implements PersonDao {

	private static final String TABLE_NAME = "PERSON";
	
	private String[] columnPerson = { "id", "name", "documentNumber" };

	private SQLiteDatabase sqLiteDatabase;

	public PersonDaoImpl(Context context) {
		super(context, "bd_prueba", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table" + TABLE_NAME + 
				"( id integer primary key autoincrement,"
				+ " name varchar(20) not null,"
				+ " documentNumber varchar(8) not null)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists " + TABLE_NAME);
		onCreate(db);
	}

	// Metodos para manejar la BD
	@Override
	public void open() {
		sqLiteDatabase = this.getWritableDatabase();
	}

	@Override
	public void close() {
		sqLiteDatabase.close();
	}

	@Override
	public long insertPerson(Person person) {
		ContentValues values = new ContentValues();
		
		values.put(columnPerson[1], person.getName());
		values.put(columnPerson[2], person.getDocumentNumber());
		
		return sqLiteDatabase.insert(TABLE_NAME, null, values);
	}

	@Override
	public long deleteById(Integer id) {
		return sqLiteDatabase.delete(TABLE_NAME, columnPerson[0].concat(" = ") + id, null);
	}

	@Override
	public long updatePerson(Person person) {
		ContentValues values = new ContentValues();
		
		values.put(columnPerson[1], person.getName());
		values.put(columnPerson[2], person.getDocumentNumber());
		
		return sqLiteDatabase.update(TABLE_NAME, values, columnPerson[0].concat(" = ") + person.getId(), null);
	}

	@Override
	public Cursor findAll() {
		return sqLiteDatabase.query(TABLE_NAME, columnPerson, null, null, null, null, null);

	}

	@Override
	public String[] findByName(String name) {
		// TODO Auto-generated method stub
		String[] data = new String[3];
		String dao = "select * from" + TABLE_NAME + " where" + columnPerson[1].concat(" = '") + name + "';";
		Cursor c = sqLiteDatabase.rawQuery(dao, null);
		if (c.moveToFirst()) {
			for (int i = 0; i < 3; i++) {
				data[i] = c.getString(i);
			}
		}

		return data;
	}

}
