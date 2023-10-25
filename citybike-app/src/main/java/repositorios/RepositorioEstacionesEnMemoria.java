package repositorios;

import java.util.ArrayList;
import java.util.List;

import modelo.Estacion;

public class RepositorioEstacionesEnMemoria implements RepositorioEstacion{
	
	List<Estacion> estaciones = new ArrayList<>();
	
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
