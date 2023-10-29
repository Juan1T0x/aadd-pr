package servicios;

import java.util.List;

import external.ServicioDBpediaImpl;
import external.ServicioGeoNamesImpl;
import modelo.SitioTuristico;
import modelo.SitioTuristicoCompleto;

public class ServicioSitiosTuristicos implements IServicioSitiosTuristicos{

	private static ServicioDBpediaImpl servicioDBpedia = ServicioDBpediaImpl.getInstance();
	private static ServicioGeoNamesImpl servicioGeoNames = ServicioGeoNamesImpl.getInstance();
	
	private static ServicioSitiosTuristicos instance = new ServicioSitiosTuristicos();
	
	private ServicioSitiosTuristicos() {
		
	}
	
	public static ServicioSitiosTuristicos getInstance() {
		return instance;
	}
	
	@Override
	public List<SitioTuristico> obtenerSitiosTuristicosInteres(double latitud, double longitud) {
		List<SitioTuristico> sitiosTuristicos = servicioGeoNames.obtenerSitioTuristicoInteres(latitud, longitud);
		return sitiosTuristicos;
		
	}

	@Override
	public SitioTuristicoCompleto obtenerInformacionSitioTuristico(String id) {
		SitioTuristicoCompleto sitioTuristico = servicioDBpedia.obtenerInformacionSitioTuristico(id);
		return sitioTuristico;
	}



}
