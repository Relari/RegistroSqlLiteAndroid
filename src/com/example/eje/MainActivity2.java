package com.example.eje;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity2 extends Activity {

	private ListView lista;

	List<String> item = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity2);

		lista = (ListView) findViewById(R.id.listVista);

		mostrarCon();
	}

	public void mostrarCon() {
		PersonDao personDao = new PersonDaoImpl(this);
		personDao.open();
		Cursor c = personDao.findAll();
		item = new ArrayList<String>();
		String id="", nombre = "", dni = "";
		if (c.moveToFirst()) {
			do {
				id = c.getString(0);
				nombre = c.getString(1);
				dni = c.getString(2);
				item.add(id + " - " + nombre + " - " + dni);
			} while (c.moveToNext());
		}
		ArrayAdapter<String>adaptador=
				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,item);
		lista.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity2, menu);
		return true;
	}

}
