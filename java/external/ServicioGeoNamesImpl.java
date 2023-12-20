package external;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import modelo.SitioTuristico;

public class ServicioGeoNamesImpl implements ServicioGeoNames {
	
	private static ServicioGeoNamesImpl instance = new ServicioGeoNamesImpl();
	
	private ServicioGeoNamesImpl() {
		
	}
	
	public static ServicioGeoNamesImpl getInstance() {
		return instance;
	}

	@Override
	public List<SitioTuristico> obtenerSitioTuristicoInteres(double latitud, double longitud) {

		List<SitioTuristico> sitiosTuristicosInteres = new ArrayList<>();

		try {

			String usuario = "joseangel.um";
			String url = "http://api.geonames.org/findNearbyWikipedia?lat=" + latitud + "&lng=" + longitud
					+ "&username=" + usuario;

			// Paso 1: Solicitud de conexcion al API de GeoNames
			URL geoNamesURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) geoNamesURL.openConnection();
			connection.setRequestMethod("GET");

			// Paso 2: Obtener la respuesta
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();

				// Paso 3: Procesar la respuesta XML
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(inputStream);

				// Paso 4: Accedemos a los elementos relevantes del XML (nombres y resumenes)
				NodeList entries = doc.getElementsByTagName("entry");

				// Paso 5: Procesamos los datos obtenidos
				for (int i = 0; i < entries.getLength(); i++) {
					Element entry = (Element) entries.item(i);
					String nombre = entry.getElementsByTagName("title").item(0).getTextContent();
					String descripcion = entry.getElementsByTagName("summary").item(0).getTextContent();
					String distanciaStr = entry.getElementsByTagName("distance").item(0).getTextContent(); // Distancia
																											// en
																											// kilometros
					double distancia = Double.parseDouble(distanciaStr);
					String urlWikipedia = entry.getElementsByTagName("wikipediaUrl").item(0).getTextContent();
					SitioTuristico sitioTuristico = new SitioTuristico(nombre, descripcion, distancia, urlWikipedia);
					sitiosTuristicosInteres.add(sitioTuristico);
				}
			} else {
				System.out.println("Error al conectar a DBpedia. Código de respuesta: " + responseCode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sitiosTuristicosInteres;
	}

	public static void main(String[] args) {
		ServicioGeoNamesImpl servicioGeoNames = new ServicioGeoNamesImpl();
		double lat = 37.98692;
		double lng = -1.12497;
		List<SitioTuristico> sitiosTuristicos = servicioGeoNames.obtenerSitioTuristicoInteres(lat, lng);
		for (int i = 0; i < sitiosTuristicos.size(); i++) {
			System.out.println(sitiosTuristicos.get(i));
		}
	}

}
