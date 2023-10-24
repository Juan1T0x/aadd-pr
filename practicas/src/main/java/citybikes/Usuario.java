package citybikes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String email;
    private Date fechaDeNacimiento;
    private String telefono;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String coordenadas;
 

    //Constructor de Usuarios
    public Usuario(String email, Date fechaDeNacimiento, String telefono, String nombre, String apellidos) {
        this.email = email;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellidos = apellidos;
       
    }
    
    public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	//Función alquilar Bici
    public void alquilarBici(Bici bici) {
        if (!bici.isAlquilada) {
            bici.alquilar();
            System.out.println(nombre + " ha alquilado la bici con código: " + bici.getCodigo());
        } else {
            System.out.println("Esta bici ya está alquilada.");
        }
    }
    
    //Función crear Incidencia.
    public Incidencia crearIncidencia(String descripcion, Bici bicicletaAfectada) {
        Incidencia incidencia = new Incidencia(descripcion, bicicletaAfectada);
        return incidencia;
    }
    
    //Ver puntos de prestamo 
    //TODO
    

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", fechaDeNacimiento=" + fechaDeNacimiento + ", telefono=" + telefono
				+ ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
    
    
    
    

}
