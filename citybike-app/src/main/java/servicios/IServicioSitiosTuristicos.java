package servicios;

import java.util.List;

import modelo.SitioTuristico;
import modelo.SitioTuristicoCompleto;

public interface IServicioSitiosTuristicos {
	List<SitioTuristico> obtenerSitiosTuristicosInteres(double latitud, double longitud);
	SitioTuristicoCompleto obtenerInformacionSitioTuristico(String id);
}
