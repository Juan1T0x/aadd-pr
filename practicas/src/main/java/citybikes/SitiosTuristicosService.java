package citybikes;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

public class SitiosTuristicosService {
   
    
	  public void obtenerInformacionWikipedia(String wikipediaURL) {
	        try {
	            // Paso 1: Extraer el título de la página de Wikipedia desde la URL
	            String pageTitle = extractPageTitle(wikipediaURL);

	            // Paso 2: Construir la URL de consulta a DBpedia
	            String dbpediaURL = "https://es.dbpedia.org/data/" + pageTitle + ".json";

	            // Paso 3: Realizar una solicitud HTTP a DBpedia
	            URL url = new URL(dbpediaURL);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");

	            // Obtener la respuesta
	            int responseCode = connection.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                InputStream inputStream = connection.getInputStream();

	                // Paso 4: Procesar la respuesta JSON
	                JsonReader jsonReader = Json.createReader(inputStream);
	                JsonObject jsonObject = jsonReader.readObject();
	                jsonReader.close();

	                // Acceder a los datos específicos en DBpedia
	                JsonObject pageData = jsonObject.getJsonObject(dbpediaURL);
	                if (pageData != null) {
	                    String name = pageData.getJsonObject("http://www.w3.org/2000/01/rdf-schema#label").getString("value");
	                    String abstractDescription = pageData.getJsonObject("http://dbpedia.org/ontology/abstract").getString("value");

	                    // Obtener las categorías
	                    JsonArray categoriesArray = pageData.getJsonArray("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
	                    List<String> categories = new ArrayList<>();
	                    for (JsonValue categoryValue : categoriesArray) {
	                        if (categoryValue.getValueType() == JsonValue.ValueType.OBJECT) {
	                            String category = ((JsonObject) categoryValue).getString("value");
	                            categories.add(category);
	                        }
	                    }

	                    // Obtener los enlaces externos
	                    JsonArray externalLinksArray = pageData.getJsonArray("http://dbpedia.org/ontology/wikiPageExternalLink");

	                    // Obtener la imagen de Wikipedia
	                    String imageUrl = pageData.getString("http://es.dbpedia.org/property/imagen");

	                    // Cerrar la conexión
	                    connection.disconnect();

	                    // Imprimir los resultados
	                    System.out.println("Nombre: " + name);
	                    System.out.println("Resumen: " + abstractDescription);
	                    System.out.println("Categorías: " + categories);
	                    System.out.println("Enlaces Externos: " + externalLinksArray);
	                    System.out.println("Imagen de Wikipedia: " + imageUrl);
	                } else {
	                    System.err.println("No se encontró información para la URL de DBpedia: " + dbpediaURL);
	                }
	            } else {
	                System.err.println("Error al conectar a DBpedia. Código de respuesta: " + responseCode);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	

    private String extractPageTitle(String wikipediaURL) {
        // Utilizar una expresión regular para extraer el título de la página desde la URL de Wikipedia
        Pattern pattern = Pattern.compile("https://[^/]+/wiki/(.*)");
        Matcher matcher = pattern.matcher(wikipediaURL);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("La URL de Wikipedia no es válida");
        }
    }
    
    public void obtenerSitiosDeInteres(double latitud, double longitud) {
        try {
            String usuario = "joseangel.um";  // Reemplaza con tu usuario de GeoNames
            String url = "http://api.geonames.org/findNearbyWikipedia?lat=" + latitud + "&lng=" + longitud + "&username=" + usuario;

            // Paso 1: Realizar una solicitud HTTP al API de GeoNames
            URL geoNamesURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) geoNamesURL.openConnection();
            connection.setRequestMethod("GET");

            // Obtener la respuesta
            InputStream inputStream = connection.getInputStream();

            // Paso 2: Procesar la respuesta XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(inputStream);

            // Acceder a los elementos relevantes en el XML (por ejemplo, nombres y resúmenes de sitios)
            NodeList entries = doc.getElementsByTagName("entry");

            // Procesar los datos y mostrarlos en la consola
            for (int i = 0; i < entries.getLength(); i++) {
                org.w3c.dom.Element entry = (org.w3c.dom.Element) entries.item(i);
                String name = entry.getElementsByTagName("title").item(0).getTextContent();
                String summary = entry.getElementsByTagName("summary").item(0).getTextContent();
                String distance = entry.getElementsByTagName("distance").item(0).getTextContent();
                String wikipediaURL = entry.getElementsByTagName("wikipediaUrl").item(0).getTextContent();

                System.out.println("Nombre del sitio: " + name);
                System.out.println("Breve descripción: " + summary);
                System.out.println("Distancia a las coordenadas de búsqueda: " + distance + " metros");
                System.out.println("URL de su artículo en Wikipedia: " + wikipediaURL);
                System.out.println();
            }

            // Cerrar la conexión
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    
    

    public static void main(String[] args) {
        SitiosTuristicosService sitiosTuristicos = new SitiosTuristicosService();
        double latitud = 41.4036299; // Latitud de la Sagrada Familia en Barcelona
        double longitud = 2.1743558; // Longitud de la Sagrada Familia en Barcelona
        sitiosTuristicos.obtenerSitiosDeInteres(latitud, longitud);
        System.out.println("-------------------------");
        
        double latitud2 =  37.9838; // Latitud de la Sagrada Familia en Barcelona
        double longitud2 =  -1.1292; // Longitud de la Sagrada Familia en Barcelona
        sitiosTuristicos.obtenerSitiosDeInteres(latitud2, longitud2);
        System.out.println("-------------------------");
        SitiosTuristicosService sitiosTuristicos2 = new SitiosTuristicosService();
        String wikipediaURL = "https://es.wikipedia.org/wiki/Torre_Eiffel";
        
        System.out.println(sitiosTuristicos2.extractPageTitle("https://es.wikipedia.org/wiki/Torre_Eiffel"));
        System.out.println("-------------------------");
        SitiosTuristicosService sitiosTuristicos3 = new SitiosTuristicosService();
        String wikipediaURL2 = "https://es.wikipedia.org/wiki/Torre_Eiffel";
        sitiosTuristicos3.obtenerInformacionWikipedia(wikipediaURL2);
    }
        
    }


