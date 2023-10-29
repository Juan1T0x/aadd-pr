package repositorios;

import java.util.ArrayList;
import java.util.List;

import modelo.Estacion;

public class RepositorioEstacionesEnMemoria implements RepositorioEstacion{
	
	private List<Estacion> estaciones = new ArrayList<>();
	
	//TODO: Singleton
	private static RepositorioEstacionesEnMemoria instance = new RepositorioEstacionesEnMemoria();
	
	private RepositorioEstacionesEnMemoria() {
		
	}
	
	public static RepositorioEstacionesEnMemoria getInstance() {
		return instance;
	}
	
	@Override
	public Estacion saveEstacion(Estacion estacion) {
		estaciones.add(estacion);
		return estacion;
	}

	@Override
	public Estacion findEstacionById(String id) {
		for(Estacion estacion : estaciones) {
			if(estacion.getId().equals(id)) {
				return estacion;
			}
		}
		return null;
	}

	@Override
	public List<Estacion> getAllEstaciones() {
		return estaciones;
	}

}
