package cache;

import java.util.ArrayList;
import java.util.List;

import modelo.SitioTuristicoCompleto;

public class RepositorioCacheEnMemoria implements RepositorioCache {

	List<SitioTuristicoCompleto> sitiosTuristicosCompletos = new ArrayList<>();
	
	// Usar JSON-B

	@Override
	public SitioTuristicoCompleto saveSitioTuristicoCompleto(SitioTuristicoCompleto sitioTuristicoCompleto) {
		sitiosTuristicosCompletos.add(sitioTuristicoCompleto);
		// Guardar en el JSON
		return sitioTuristicoCompleto;
	}

	@Override
	public SitioTuristicoCompleto findSitioTuristicoCompletoByURL(String url) {
		// Recorrer el JSON
		for (SitioTuristicoCompleto sitioTuristicoCompleto : sitiosTuristicosCompletos) {
			if (sitioTuristicoCompleto.getUrlWikipedia().equals(url)) {
				return sitioTuristicoCompleto;
			}
		}
		return null;
	}

	@Override
	public List<SitioTuristicoCompleto> getAllSitiosTuristicosCompletos() {
		// Recorrer el JSON
		return sitiosTuristicosCompletos;
	}

}
