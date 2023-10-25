package modelo;

import java.util.Date;

public class Usuario {
	
	// Propiedades
	
	private String email;
	private Date fechaNac;
	private String telefono;
	private String nombreCompleto;
	
	// Constructor
	
	public Usuario(String email, Date fechaNac, String telefono, String nombreCompleto) {
		this.email = email;
		this.fechaNac = fechaNac;
		this.telefono = telefono;
		this.nombreCompleto = nombreCompleto;
	}
	
	// Getters y setters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	
	
}
