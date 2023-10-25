package external;

import java.util.List;

import modelo.SitioTuristico;

public interface ServicioGeoNames {
	List<SitioTuristico> obtenerSitioTuristicoInteres(double latitud, double longitud);
}
