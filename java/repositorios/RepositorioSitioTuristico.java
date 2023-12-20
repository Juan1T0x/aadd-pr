package repositorios;

import java.util.List;

import modelo.SitioTuristico;

public interface RepositorioSitioTuristico {
	SitioTuristico saveSitioTuristico(SitioTuristico sitioTuristico);
	SitioTuristico findSitioTuristico(String nombre);
	List<SitioTuristico> getAllSitiosTuristicos();
}
