package external;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import modelo.SitioTuristicoCompleto;

public class ServicioDBpediaImpl implements ServicioDBpedia{

	@Override
	public SitioTuristicoCompleto obtenerInformacionSitioTuristico(String nombre) {
		SitioTuristicoCompleto sitiosTuristico;
		// Utilizar JSON-P
		try {
			
			// Paso 1: Construir la URL de consulta a DBpedia
			String dbpediaURL = "http://es.debepedia.org/data/" + nombre + ".json";
			
			// Paso 2: Realizar una solicitud HTTP a DBpedia
			URL url = new URL(dbpediaURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			// Paso 3: Obtener la respuesta
			int responseCode = connection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				
				// Paso 4: Procesar la respuesta JSON
				InputStreamReader reader = new InputStreamReader(inputStream);
				JsonReader jsonReader = Json.createReader(reader);
				JsonObject json = jsonReader.readObject();
				
			}
			else {
				System.out.println("Erro al conectar a DBpedia. Código de respuesta: " + responseCode);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
