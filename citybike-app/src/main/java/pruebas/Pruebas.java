package pruebas;

import repositorios.RepositorioEstacionesEnMemoria;
import repositorios.RepositorioSitiosTuristicosEnMemoria;
import servicios.ServicioEstaciones;

public class Pruebas {

	public static void main(String[] args) {
		
		RepositorioEstacionesEnMemoria repoEstacion = new RepositorioEstacionesEnMemoria();
		RepositorioSitiosTuristicosEnMemoria repoSitios = new RepositorioSitiosTuristicosEnMemoria();
		
		ServicioEstaciones servicioEstaciones = new ServicioEstaciones(repoEstacion,repoSitios);
		
		servicioEstaciones.altaEstacion("Merced", 5, "Calle Merced", 10.0, 10.0);
		System.out.println(servicioEstaciones.obtenerTodasEstaciones());
	}

}
