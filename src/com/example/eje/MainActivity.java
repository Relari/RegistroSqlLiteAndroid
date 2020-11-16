package com.example.eje;

import com.example.eje.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	EditText txtnombre, txtdni, txtid;
	Button btnguardar, btnmostrar, btneliminar, btnmodificar, btnbuscar, btnlimpiar;

	Persona per = new Persona();

	public void Limpiar() {
		txtid.setText(null);
		txtnombre.setText(null);
		txtdni.setText(null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		txtnombre = (EditText) findViewById(R.id.txtnombre);
		txtdni = (EditText) findViewById(R.id.txtdni);
		btnguardar = (Button) findViewById(R.id.btnguardar);
		btnmostrar = (Button) findViewById(R.id.btnmostrar);
		txtid = (EditText) findViewById(R.id.txtid);
		btneliminar = (Button) findViewById(R.id.btneliminar);
		btnmodificar = (Button) findViewById(R.id.btnmodificar);
		btnbuscar = (Button) findViewById(R.id.btnbuscar);
		btnlimpiar = (Button) findViewById(R.id.btnlimpiar);

		btnguardar.setOnClickListener(this);
		btnmodificar.setOnClickListener(this);
		btneliminar.setOnClickListener(this);
		btnmostrar.setOnClickListener(this);
		btnbuscar.setOnClickListener(this);
		btnlimpiar.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.btnlimpiar:
			Limpiar();
			break;
		case R.id.btnguardar:
			try {
				BDAyuda bda = new BDAyuda(this);
				bda.abrir();
				
				per.setNombre(txtnombre.getText().toString());
				per.setDNI(txtdni.getText().toString());

				long result = bda.registrar(per);

				bda.cerrar();
				if (result > 0) {
					Toast t = Toast.makeText(this, "valor insertado",
							Toast.LENGTH_LONG);
					t.show();
					Limpiar();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Toast t = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
				t.show();
			}
			break;
		case R.id.btnmodificar:
			try {
				BDAyuda bda = new BDAyuda(this);
				bda.abrir();

				per.setId_registro(Integer.parseInt(txtid.getText().toString()));
				per.setNombre(txtnombre.getText().toString());
				per.setDNI(txtdni.getText().toString());
				
				long result = bda.modificar(per);

				bda.cerrar();
				if (result > 0) {
					Toast t = Toast.makeText(this, "valor modificado",
							Toast.LENGTH_LONG);
					t.show();
					Limpiar();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Toast t = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
				t.show();
			}
			break;
		case R.id.btnmostrar:
			Intent sgte = new Intent(MainActivity.this, MainActivity2.class);
			startActivity(sgte);
			break;
		case R.id.btneliminar:
			try {
				BDAyuda bda = new BDAyuda(this);
				bda.abrir();
				
				per.setId_registro(Integer.parseInt(txtid.getText().toString()));
				
				bda.eliminar(per);
				bda.cerrar();

				Toast t = Toast.makeText(this, "valor eliminado", Toast.LENGTH_LONG);
				t.show();
				Limpiar();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Toast t = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
				t.show();
			}
			break;
		case R.id.btnbuscar:
			BDAyuda bda = new BDAyuda(this);
			bda.abrir();
			
			per.setNombre(txtnombre.getText().toString());
			
			String[] datos = bda.buscador(per);
			txtid.setText(datos[0]);
			txtnombre.setText(datos[1]);
			txtdni.setText(datos[2]);
			bda.cerrar();
			break;
		}
	}

}
