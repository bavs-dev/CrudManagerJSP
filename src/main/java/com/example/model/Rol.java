package com.example.model;

public class Rol {

	// creacion de los parametros de nuestra tabla
	private int id;
	private String nombre ;
	private String verificador;

	//constructor
	public Rol() {}

	//constructor con parametros
	public Rol(int id, String nombre, String verificador) {
		this.id = id;
		this.nombre = nombre;
		this.verificador = verificador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVerificador() {
		return verificador;
	}

	public void setVerificador(String verificador) {
		this.verificador = verificador;
	}



}
