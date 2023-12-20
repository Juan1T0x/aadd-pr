package external;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import cache.RepositorioCacheImpl;
import modelo.SitioTuristicoCompleto;

public class ServicioDBpediaImpl implements ServicioDBpedia {

	// Instanciamos el repositorio cache
	private RepositorioCacheImpl repositorioCache = RepositorioCacheImpl.getInstance();

	private static ServicioDBpediaImpl instance = new ServicioDBpediaImpl();

	private ServicioDBpediaImpl() {

	}

	public static ServicioDBpediaImpl getInstance() {
		return instance;
	}

	public SitioTuristicoCompleto obtenerInformacionSitioTuristico(String idInicio) {

		String id;

		// Si el nombre que recibimos es una URL de wikipedia, procesamos el enlace para
		// obtener el nombre del sitio
		if (idInicio.startsWith("https://")) {
			id = extractPageTitle(idInicio);
			// Si recibimos un nombre bien formateado, empezamos a preparar la conexion
		} else {
			id = idInicio;
		}

		// Debug
		// System.out.println(nombre);

		// Declaramos una instancia del objeto sitioTuristico sin inicializar
		SitioTuristicoCompleto sitioTuristico;

		// Primero comprobar que se encuentra en cache
		sitioTuristico = repositorioCache.findSitioTuristicoCompletoById(id);
		if (sitioTuristico.getId() != null) {
			// Debug
			System.out.println(id + " se encontraba en cache y no se ha realizado una peticion a la API");
			return sitioTuristico;
		} else {
			try {

				// Creamos el URL del sitio al que queremos acceder
				String dbpediaURLstr = "https://es.dbpedia.org/data/" + URLEncoder.encode(id, "UTF-8") + ".json";

				// Creamos las conexion a esta URL
				URL dbPediaURL = new URL(dbpediaURLstr);
				HttpURLConnection connection = (HttpURLConnection) dbPediaURL.openConnection();
				connection.setRequestMethod("GET");

				int responseCode = connection.getResponseCode();

				// Si la conexion es correcta
				if (responseCode == HttpURLConnection.HTTP_OK) {

					// Inicializamos el objeto
					sitioTuristico = new SitioTuristicoCompleto(id, " ", 0, idInicio, "", null, null, null, null);
					sitioTuristico.setId(id);
					
					InputStream inputStream = connection.getInputStream();
					InputStreamReader reader = new InputStreamReader(inputStream);

					// Deserializar el JSON
					JsonReader jsonReader = Json.createReader(reader);
					JsonObject jsonObject = jsonReader.readObject();

					String baseUrl = "http://es.dbpedia.org/resource/" + id;

					if (jsonObject.containsKey(baseUrl)) {

						JsonObject resourceObject = jsonObject.getJsonObject(baseUrl);

						// Obtener el nombre
						if (resourceObject.containsKey("http://www.w3.org/2000/01/rdf-schema#label")) {
							JsonArray nombreSitio = resourceObject
									.getJsonArray("http://www.w3.org/2000/01/rdf-schema#label");
							for (int i = 0; i < nombreSitio.size(); i++) {
								JsonObject nombreSitioObject = nombreSitio.getJsonObject(i);
								if (nombreSitioObject.getString("lang", "").equals("es")) {
									String nombreSitioText = nombreSitioObject.getString("value");
									sitioTuristico.setNombre(nombreSitioText); //
									break;
								}
							}
						}

						// Obtener el resumen (abstract)
						if (resourceObject.containsKey("http://dbpedia.org/ontology/abstract")) {
							JsonArray abstracts = resourceObject.getJsonArray("http://dbpedia.org/ontology/abstract");
							for (int i = 0; i < abstracts.size(); i++) {
								JsonObject abstractObject = abstracts.getJsonObject(i);
								if (abstractObject.getString("lang", "").equals("es")) {
									String abstractText = abstractObject.getString("value");
									sitioTuristico.setResumen(abstractText); //
									break;
								}
							}
						}
						// Obtener Imagen
						if (resourceObject.containsKey("http://es.dbpedia.org/property/imagen")) {
							String imageUrl = resourceObject.getJsonArray("http://es.dbpedia.org/property/imagen")
									.getJsonObject(0).getString("value");
							sitioTuristico.setImagen(imageUrl);
						}

						// Obtener categorias
						if (resourceObject.containsKey("http://purl.org/dc/terms/subject")) {
							JsonArray categoriesArray = resourceObject.getJsonArray("http://purl.org/dc/terms/subject");
							List<String> categories = new ArrayList<>();
							for (int i = 0; i < categoriesArray.size(); i++) {
								String categoryUrl = categoriesArray.getJsonObject(i).getString("value");
								String categoryName = categoryUrl.substring(categoryUrl.lastIndexOf('/') + 1);
								categories.add(categoryName);
							}
							sitioTuristico.setCategorias(categories);
						}
						// Obtener Enlaces Externos
						if (resourceObject.containsKey("http://dbpedia.org/ontology/wikiPageExternalLink")) {
							List<String> externalLinks = new ArrayList<>();
							JsonArray externalLinksArray = resourceObject
									.getJsonArray("http://dbpedia.org/ontology/wikiPageExternalLink");
							for (int i = 0; i < externalLinksArray.size(); i++) {
								String link = externalLinksArray.getJsonObject(i).getString("value");
								externalLinks.add(link);
							}
							sitioTuristico.setEnlacesComplementarios(externalLinks);

						}

					}

					// Guardamos el sitio turistico en la cache
					System.out.println(
							id + " no se encontraba en la cache y ha sido añadido tras realizar la peticion");
					repositorioCache.saveSitioTuristicoCompleto(sitioTuristico);
					return sitioTuristico;

				} else {
					System.out.println("Error al conectar a DBpedia. Código de respuesta: " + responseCode);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Si no se encuentra en cache

		return null;
	}

	private String extractPageTitle(String wikipediaURL) {
		// Extraer el título de la página de Wikipedia a partir de la URL
		int lastSlashIndex = wikipediaURL.lastIndexOf("/");
		return wikipediaURL.substring(lastSlashIndex + 1);
	}

	public static void main(String[] args) {

		ServicioDBpediaImpl servicio = new ServicioDBpediaImpl();
		SitioTuristicoCompleto sitio = servicio
				.obtenerInformacionSitioTuristico("https://es.wikipedia.org/wiki/Gran_Pirámide_de_Guiza");
		SitioTuristicoCompleto sitio2 = servicio
				.obtenerInformacionSitioTuristico("https://es.wikipedia.org/wiki/Catedral_de_Murcia");
	}

}