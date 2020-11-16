package com.example.eje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class BDAyuda extends SQLiteOpenHelper {

	Context ctx;

	public BDAyuda(Context context) {
		super(context, "bd_prueba", null, 1);
		// TODO Auto-generated constructor stub
		ctx = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table registro ("
				+ " id_registro integer primary key autoincrement,"
				+ " nombre varchar(20) not null," + " dni varchar(8) not null)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists registro");
		onCreate(db);
	}

	BDAyuda ayuda;
	SQLiteDatabase db;

	// Metodos para manejar la BD
	public void abrir() {
		ayuda = new BDAyuda(ctx);
		db = ayuda.getWritableDatabase();
	}

	public void cerrar() {
		db.close();
	}

	public long registrar(Persona p) throws Exception {
		ContentValues valores = new ContentValues();
		valores.put("nombre", p.getNombre());
		valores.put("dni", p.getDNI());
		return db.insert("registro", null, valores);
	}

	public long eliminar(Persona p) throws Exception {
		return db.delete("registro", "id_registro = " + p.getId_registro(), null);
	}

	public long modificar(Persona p)
			throws Exception {
		ContentValues valores = new ContentValues();
		valores.put("nombre", p.getNombre());
		valores.put("dni", p.getDNI());
		return db.update("registro", valores, "id_registro = " + p.getId_registro(), null);
	}

	public Cursor consultarD() {
		String[] columnas = { "id_registro", "nombre", "dni" };
		return db.query("registro", columnas, null, null, null, null, null);

	}

	public String[] buscador(Persona p) {
		// TODO Auto-generated method stub
		String[] datos = new String[3];
		String dao = "select * from registro where nombre ='" + p.getNombre() + "';";
		Cursor c = db.rawQuery(dao, null);
		if(c.moveToFirst()){
			for (int i =0; i<3;i++) {
				datos[i]= c.getString(i);
			}
		}

		return datos;
	}

}
