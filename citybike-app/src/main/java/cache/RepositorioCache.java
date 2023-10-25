package cache;

import java.util.List;

import modelo.SitioTuristicoCompleto;

public interface RepositorioCache {
	SitioTuristicoCompleto saveSitioTuristicoCompleto(SitioTuristicoCompleto sitioTuristicoCompleto);
	SitioTuristicoCompleto findSitioTuristicoCompletoByURL(String url);
	List<SitioTuristicoCompleto> getAllSitiosTuristicosCompletos();
}
