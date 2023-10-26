package external;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import modelo.SitioTuristicoCompleto;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;

public class ServicioDBpediaImpl implements ServicioDBpedia {

	public SitioTuristicoCompleto obtenerInformacionSitioTuristico(String nombreInicio) {
		String nombre;
		SitioTuristicoCompleto sitioTuristico = new SitioTuristicoCompleto(nombreInicio," " , 0, nombreInicio,
				nombreInicio, null, null, null); 
		if (nombreInicio.startsWith("https://")) {
			nombre = extractPageTitle(nombreInicio);
		} else {
			nombre = nombreInicio;
		}
		System.out.println(nombre);

		try {
			String dbpediaURL = "https://dbpedia.org/data/" + URLEncoder.encode(nombre, "UTF-8") + ".json";

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(dbpediaURL)).GET().build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) {
				String responseBody = response.body();

				// Deserializar el JSON
				JsonReader jsonReader = Json.createReader(new StringReader(responseBody));
				JsonObject jsonObject = jsonReader.readObject();

				//System.out.println(jsonObject);
				String baseUri = "http://dbpedia.org/resource/" + nombre; // Nota: Cambié page a resource para obtener
																			// el recurso correcto
				// System.out.println(baseUri);

				if (jsonObject.containsKey(baseUri)) {
					JsonObject resourceObject = jsonObject.getJsonObject(baseUri);
					String label = resourceObject.containsKey("http://www.w3.org/2000/01/rdf-schema#label")
							? resourceObject.getJsonArray("http://www.w3.org/2000/01/rdf-schema#label").getJsonObject(0)
									.getString("value")
							: "";
					String uri = baseUri;
					System.out.print("Se mete en el bucle");
					System.out.print(".\n");
					sitioTuristico.setNombre(label);
					sitioTuristico.setUrlWikipedia(uri);

					// Obtener el resumen (abstract)
					if (resourceObject.containsKey("http://dbpedia.org/ontology/abstract")) {
						JsonArray abstracts = resourceObject.getJsonArray("http://dbpedia.org/ontology/abstract");
						for (int i = 0; i < abstracts.size(); i++) {
							JsonObject abstractObject = abstracts.getJsonObject(i);
							if (abstractObject.getString("lang", "").equals("en")) { // ajustar a "es" para español si
																						// es necesario
								String abstractText = abstractObject.getString("value");
								sitioTuristico.setResumen(abstractText); //
								break;
							}
						}
					}
					// Obtener Imagen
					if (resourceObject.containsKey("http://xmlns.com/foaf/0.1/depiction")) {
						String imageUrl = resourceObject.getJsonArray("http://xmlns.com/foaf/0.1/depiction")
								.getJsonObject(0).getString("value");
						try {
							URL url = new URL(imageUrl);
							Image image = ImageIO.read(url);
							sitioTuristico.setImagen(image);
						} catch (IOException e) {
							System.out.println("Error al descargar la imagen: " + e.getMessage());
						}
					}

					// Obtener categorias
					if (resourceObject.containsKey("http://purl.org/dc/terms/subject")) {
						JsonArray categoriesArray = resourceObject.getJsonArray("http://purl.org/dc/terms/subject");
						List<String> categories = new ArrayList<>();
						for (int i = 0; i < categoriesArray.size(); i++) {
							String categoryUri = categoriesArray.getJsonObject(i).getString("value");
							// Aquí, estamos extrayendo el último segmento del URI como nombre de la
							// categoría.
							// Es una simplificación y podrías querer hacer algo más elaborado.
							String categoryName = categoryUri.substring(categoryUri.lastIndexOf('/') + 1);
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
						sitioTuristico.setEnlacesComplementarios(externalLinks); // Asume que hay un método
																					// setEnlacesExternos en
																					// SitioTuristicoCompleto que acepta
																					// una lista de strings
					}

				}
				
				return sitioTuristico;
				
			} else {
				System.out.println("Error al conectar a DBpedia. Código de respuesta: " + response.statusCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(sitioTuristico);
		return null;
	}

	private String extractPageTitle(String wikipediaURL) {
		// Extraer el título de la página de Wikipedia a partir de la URL
		int lastSlashIndex = wikipediaURL.lastIndexOf("/");
		return wikipediaURL.substring(lastSlashIndex + 1);
	}



	public static void main(String[] args) {

		ServicioDBpediaImpl servicio = new ServicioDBpediaImpl();
		SitioTuristicoCompleto sitio  = servicio.obtenerInformacionSitioTuristico("https://en.wikipedia.org/wiki/Great_Pyramid_of_Giza");
		System.out.println(sitio);
	}

}