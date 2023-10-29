package modelo;

import java.util.Date;
import java.util.List;

import servicios.ServicioEstaciones;

public class Administrador extends Usuario {
	
	private static ServicioEstaciones servicioEstaciones = ServicioEstaciones.getInstance();

	public Administrador(String email, Date fechaNac, String telefono, String nombreCompleto) {
		super(email, fechaNac, telefono, nombreCompleto);
	}
	
	public String altaEstacion(String nombre, int numeroPuestos, String direccion, double latitud, double longitud) {
		return servicioEstaciones.altaEstacion(nombre, numeroPuestos, direccion, latitud, longitud);
	}
	
	public List<SitioTuristico> obtenerSitiosTuristicosProximos(String idEstacion) {
		return servicioEstaciones.obtenerSitiosTuristicosProximos(idEstacion);
	}
	
	public void establecerSitiosTuristicos(String idEstacion, List<SitioTuristico> sitiosTuristicos) {
		servicioEstaciones.establecerSitiosTuristicos(idEstacion, sitiosTuristicos);
	}
	
	public Estacion obtenerEstacion(String idEstacion) {
		return servicioEstaciones.obtenerEstacion(idEstacion);
	}

}
