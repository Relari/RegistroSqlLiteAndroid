package com.example.eje;

public class Persona {

	private Integer id_registro;
	private String nombre;
	private String DNI;
	
	public Persona() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId_registro() {
        return id_registro;
    }

    public void setId_registro(Integer id_registro) {
        this.id_registro = id_registro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
}
