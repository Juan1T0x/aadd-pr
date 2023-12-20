package cache;

import java.util.List;

import modelo.SitioTuristicoCompleto;

public interface RepositorioCache {
	SitioTuristicoCompleto saveSitioTuristicoCompleto(SitioTuristicoCompleto sitioTuristicoCompleto);
	SitioTuristicoCompleto findSitioTuristicoCompletoById(String id);
	List<SitioTuristicoCompleto> getAllSitiosTuristicosCompletos();
}
