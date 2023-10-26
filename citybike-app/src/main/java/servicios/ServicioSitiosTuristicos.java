package servicios;

import java.util.List;

import external.ServicioDBpedia;
import external.ServicioDBpediaImpl;
import external.ServicioGeoNamesImpl;
import modelo.SitioTuristico;
import modelo.SitioTuristicoCompleto;
import repositorios.RepositorioEstacion;
import repositorios.RepositorioSitioTuristico;

public class ServicioSitiosTuristicos implements IServicioSitiosTuristicos{

	private RepositorioEstacion repositorioEstacion;
	private RepositorioSitioTuristico repositorioSitioTuristico;

	public ServicioSitiosTuristicos(RepositorioEstacion repositorioEstacion,
			RepositorioSitioTuristico repositorioSitioTuristico) {
		this.repositorioEstacion = repositorioEstacion;
		this.repositorioSitioTuristico = repositorioSitioTuristico;
	}



	@Override
	public SitioTuristico obtenerSitioTuristico(String nombre) {
		return repositorioSitioTuristico.findSitioTuristico(nombre);
	}



	@Override
	public List<SitioTuristico> obtenerSitiosTuristicosInteres(double latitud, double longitud) {
		ServicioGeoNamesImpl servicio = new ServicioGeoNamesImpl();
		return servicio.obtenerSitioTuristicoInteres(latitud, latitud);
		
	}



	@Override
	public List<SitioTuristicoCompleto> obtenerInformacionSitioTuristico(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
