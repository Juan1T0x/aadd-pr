package servicios;

import java.util.ArrayList;
import java.util.List;

import cache.RepositorioCacheImpl;
import modelo.Estacion;
import modelo.SitioTuristico;
import repositorios.RepositorioEstacionesEnMemoria;

public class ServicioEstaciones implements IServicioEstaciones {

	private static RepositorioEstacionesEnMemoria repositorioEstacion = RepositorioEstacionesEnMemoria.getInstance();
	private static ServicioSitiosTuristicos servicioSitiosTuristicos = ServicioSitiosTuristicos.getInstance();

	// Singleton
	private static ServicioEstaciones instance = new ServicioEstaciones();
	
	private ServicioEstaciones() {
		
	}
	
	public static ServicioEstaciones getInstance() {
		return instance;
	}
	@Override
	public String altaEstacion(String nombre, int numeroPuestos, String direccion, double latitud, double longitud) {
		String idEstacion = generateUniqueId();
		Estacion estacion = new Estacion(idEstacion, nombre, numeroPuestos, direccion, latitud, longitud);
		repositorioEstacion.saveEstacion(estacion);
		return idEstacion;
	}

	@Override
	public List<SitioTuristico> obtenerSitiosTuristicosProximos(String idEstacion) {
		Estacion estacion = repositorioEstacion.findEstacionById(idEstacion);
		if (estacion == null) {
			return new ArrayList<>();
		}
		double latitud = estacion.getLatidud();
		double longitud = estacion.getLongitud();

		return servicioSitiosTuristicos.obtenerSitiosTuristicosInteres(latitud, longitud);
	}

	@Override
	public void establecerSitiosTuristicos(String idEstacion, List<SitioTuristico> sitiosTuristicos) {
		Estacion estacion = repositorioEstacion.findEstacionById(idEstacion);
		if (estacion != null) {
			estacion.setSitiosTuristicosEstablecidos(sitiosTuristicos);
		}
	}

	@Override
	public Estacion obtenerEstacion(String idEstacion) {
		return repositorioEstacion.findEstacionById(idEstacion);
	}

	private String generateUniqueId() {
		return "EST-" + System.currentTimeMillis();
	}

	@Override
	public List<Estacion> obtenerTodasEstaciones() {
		return repositorioEstacion.getAllEstaciones();
	}

}
