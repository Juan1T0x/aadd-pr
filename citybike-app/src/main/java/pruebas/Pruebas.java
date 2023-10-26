package pruebas;

import java.util.List;

import external.ServicioGeoNamesImpl;
import modelo.SitioTuristico;
import repositorios.RepositorioEstacionesEnMemoria;
import repositorios.RepositorioSitiosTuristicosEnMemoria;
import servicios.ServicioEstaciones;
import servicios.ServicioSitiosTuristicos;

public class Pruebas {

	public static void main(String[] args) {
		
		RepositorioEstacionesEnMemoria repoEstacion = new RepositorioEstacionesEnMemoria();
		RepositorioSitiosTuristicosEnMemoria repoSitios = new RepositorioSitiosTuristicosEnMemoria();
		
		ServicioEstaciones servicioEstaciones = new ServicioEstaciones(repoEstacion,repoSitios);
		
		servicioEstaciones.altaEstacion("Merced", 5, "Calle Merced", 10.0, 10.0);
		System.out.println(servicioEstaciones.obtenerTodasEstaciones());
		
		
		ServicioSitiosTuristicos servicioSitiosTurist = new ServicioSitiosTuristicos(repoEstacion,repoSitios);
		
		System.out.println("------------------------");
		
		
		
		List<SitioTuristico> sitiosTuristicos = servicioSitiosTurist.obtenerSitiosTuristicosInteres(37.98692, -1.12497);
		for (int i = 0; i < sitiosTuristicos.size(); i++) {
			System.out.println(sitiosTuristicos.get(i));
			
	}

		System.out.println("------------------------");
		ServicioGeoNamesImpl servicioGeoNames = new ServicioGeoNamesImpl();
		double lat = 37.98692;
		double lng = -1.12497;
		List<SitioTuristico> sitiosTuristicos2 = servicioGeoNames.obtenerSitioTuristicoInteres(lat, lng);
		for (int i = 0; i < sitiosTuristicos2.size(); i++) {
			System.out.println(sitiosTuristicos2.get(i));
		}
		
		
	
	
	
}
}