package repositorios;

import java.util.ArrayList;
import java.util.List;

import modelo.SitioTuristico;

public class RepositorioSitiosTuristicosEnMemoria implements RepositorioSitioTuristico{
	
	private List<SitioTuristico> sitiosTuristicos = new ArrayList<>();

	@Override
	public SitioTuristico saveSitioTuristico(SitioTuristico sitioTuristico) {
		sitiosTuristicos.add(sitioTuristico);
		return sitioTuristico;
	}

	@Override
	public SitioTuristico findSitioTuristico(String nombre) {
		for(SitioTuristico sitioTuristico : sitiosTuristicos) {
			if(sitioTuristico.getNombre().equals(nombre)) {
				return sitioTuristico;
			}
		}
		return null;
	}

	@Override
	public List<SitioTuristico> getAllSitiosTuristicos() {
		return sitiosTuristicos;
	}

}
