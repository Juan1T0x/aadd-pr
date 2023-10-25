package servicios;

import java.util.List;

import modelo.SitioTuristico;
import modelo.SitioTuristicoCompleto;

public interface IServicioSitiosTuristicos {
	SitioTuristico obtenerSitioTuristico(String nombre);
	List<SitioTuristico> obtenerSitiosTuristicosInteres(double latitud, double longitud);
	List<SitioTuristicoCompleto> obtenerInformacionSitioTuristico(String nombre);
}
