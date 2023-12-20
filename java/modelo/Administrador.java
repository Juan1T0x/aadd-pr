package modelo;

import java.util.Date;


import java.io.Serializable;



public class Administrador extends Usuario implements Serializable{


	private static final long serialVersionUID = 1L;

	public Administrador(String email, Date fechaNac, String telefono, String nombreCompleto) {
		super(email, fechaNac, telefono, nombreCompleto);
	}
	

}
