package servicios;

import java.util.List;

import modelo.Bicicleta;
import modelo.Estacion;
import modelo.SitioTuristico;

public interface IServicioEstaciones {
	String altaEstacion(String nombre, int numeroPuestos, String direccion, double latitud, double longitud);
	List<SitioTuristico> obtenerSitiosTuristicosProximos(String idEstacion);
	void establecerSitiosTuristicos(String idEstacion, List<SitioTuristico> sitiosTuristicos);
	Estacion obtenerEstacion(String idEstacion);
	List<Estacion> obtenerTodasEstaciones();
	List<Bicicleta> bicicletasCercanas(double latitud, double longitud);
	List<Estacion> estacionesCercanas(double latitud, double longitud, double radio);
	
}
