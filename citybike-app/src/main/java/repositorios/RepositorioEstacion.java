package repositorios;

import java.util.List;

import modelo.Estacion;

public interface RepositorioEstacion {
	Estacion saveEstacion(Estacion estacion);
	Estacion findEstacionById(String id);
	List<Estacion> getAllEstaciones();
}
