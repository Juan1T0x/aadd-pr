package servicios;

import java.util.ArrayList;
import java.util.List;

import modelo.Estacion;
import modelo.SitioTuristico;
import repositorios.RepositorioEstacion;
import repositorios.RepositorioSitioTuristico;

public class ServicioEstaciones implements IServicioEstaciones {

	private RepositorioEstacion repositorioEstacion;
	private RepositorioSitioTuristico repositorioSitioTuristico;

	public ServicioEstaciones(RepositorioEstacion repositorioEstacion,
			RepositorioSitioTuristico repositorioSitioTuristico) {
		this.repositorioEstacion = repositorioEstacion;
		this.repositorioSitioTuristico = repositorioSitioTuristico;
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
		// TODO: Implementar la logica para obtener los sitios turisticos proximos
		
		return new ArrayList<>();
	}

	@Override
	public void establecerSitiosTuristicos(String idEstacion, List<SitioTuristico> sitiosTuristicos) {
		Estacion estacion = repositorioEstacion.findEstacionById(idEstacion);
		if(estacion != null) {
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
