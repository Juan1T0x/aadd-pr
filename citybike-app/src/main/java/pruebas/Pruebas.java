package pruebas;

import java.util.Date;
import java.util.List;

import modelo.Administrador;
import modelo.Estacion;
import modelo.SitioTuristico;
import modelo.SitioTuristicoCompleto;
import servicios.ServicioSitiosTuristicos;

public class Pruebas {

	public static void main(String[] args) {

		// Prueba 1: Probamos todas la funcionalidad del Administrador ya que de esta
		// forma probamos gran parte de los servicios ofrecidos por la aplicacion

		
		/*
		
		Administrador admin = new Administrador("juan.hernandeza@um.es", new Date(), "608517851",
				"Juan Hernández Acosta");
		String idEstacionPrueba = admin.altaEstacion("Espinardo", 3, "Campus de Espinardo", 38.019296, -1.166760);
		System.out.println("Estacion creada: " + idEstacionPrueba + "\n");
		List<SitioTuristico> sitiosProximos = admin.obtenerSitiosTuristicosProximos(idEstacionPrueba);
		System.out.println("Sitios turisticos proximos a la estacion: ");
		for (int i = 0; i < sitiosProximos.size(); i++) {
			System.out.println(sitiosProximos.get(i));
		}
		System.out.println("");
		admin.establecerSitiosTuristicos(idEstacionPrueba, sitiosProximos);
		System.out.println("Sitios establecidos para la estacion creada: ");
		Estacion estacionPrueba = admin.obtenerEstacion(idEstacionPrueba);
		for (int i = 0; i < estacionPrueba.getSitiosTuristicosEstablecidos().size(); i++) {
			System.out.println(estacionPrueba.getSitiosTuristicosEstablecidos().get(i));
		}
		*/
		
		
		System.out.println("");
		// Prueba 2: El único servicio que queda por probar es el ServicioDBpedia,
		// incluido en ServicioSitiosTuristicos
		// IMPORTANTE: Para realizar esta prueba se recomienda borrar el archivo de la
		// cache, incluido en la carpeta json

		ServicioSitiosTuristicos servicio = ServicioSitiosTuristicos.getInstance();
		SitioTuristicoCompleto sitio1 = servicio
				.obtenerInformacionSitioTuristico("https://es.wikipedia.org/wiki/Catedral_de_Murcia");
		System.out.println(sitio1 + "\n");
		SitioTuristicoCompleto sitio1cache = servicio
				.obtenerInformacionSitioTuristico("https://es.wikipedia.org/wiki/Catedral_de_Murcia");
		System.out.println(sitio1cache + "\n");
		SitioTuristicoCompleto sitio2 = servicio
				.obtenerInformacionSitioTuristico("https://es.wikipedia.org/wiki/Faro_de_Alejandría");
		System.out.println(sitio2 + "\n");

	}
}