package citybikes;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SitiosTuristicosService {

    private static final String GEO_NAMES_API = "http://api.geonames.org/findNearbyWikipediaJSON?";
    private static final String DBPEDIA_URL = "https://dbpedia.org/data/";
    private CacheManager cacheManager = new CacheManager();

    public SitioTuristico obtenerInfoDesdeDBPedia(String wikipediaPageName) {
        // Primero, verifica la caché
        SitioTuristico sitioCached = cacheManager.getFromCache(wikipediaPageName);
        if (sitioCached != null) {
            return sitioCached;
        }

        try {
            URL url = new URL(DBPEDIA_URL + URLEncoder.encode(wikipediaPageName, "UTF-8") + ".json");
            InputStream is = url.openStream();
            JsonReader rdr = Json.createReader(is);
            JsonObject obj = rdr.readObject();

            String baseUri = "http://es.dbpedia.org/resource/" + wikipediaPageName;
            JsonObject pageInfo = obj.getJsonObject(baseUri);

            String nombre = pageInfo.getJsonArray("http://www.w3.org/2000/01/rdf-schema#label").getJsonObject(0).getString("value");
            String resumen = pageInfo.getJsonArray("http://dbpedia.org/ontology/abstract").getJsonObject(0).getString("value");
            List<String> categorias = pageInfo.getJsonArray("http://www.w3.org/1999/02/22-rdf-syntax-ns#type").stream().map(JsonValue::toString).collect(Collectors.toList());
            List<String> enlacesExternos = pageInfo.containsKey("http://dbpedia.org/ontology/wikiPageExternalLink") ? pageInfo.getJsonArray("http://dbpedia.org/ontology/wikiPageExternalLink").stream().map(JsonValue::toString).collect(Collectors.toList()) : new ArrayList<>();
            String imagenWikimedia = pageInfo.getJsonArray("http://es.dbpedia.org/property/imagen").getJsonObject(0).getString("value");

            SitioTuristico sitio = new SitioTuristico();
            sitio.setNombre(nombre);
            sitio.setResumen(resumen);
            sitio.setCategorias(categorias);
            sitio.setEnlacesExternos(enlacesExternos);
            sitio.setImagenWikimedia(imagenWikimedia);

            // Guarda el resultado en la caché para uso futuro
            cacheManager.saveToCache(wikipediaPageName, sitio);

            return sitio;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SitioTuristico> obtenerSitiosDeInteres(String coordenadasGeograficas) {
        try {
        	URL geoNamesUrl = new URL(GEO_NAMES_API + "lat=" + coordenadasGeograficas.split(",")[0].trim() + "&lng=" + coordenadasGeograficas.split(",")[1].trim() + "&username=joseangel.um");
            InputStream is = geoNamesUrl.openStream();
            JsonReader geoRdr = Json.createReader(is);
            JsonObject geoObj = geoRdr.readObject();
            JsonArray geonamesArray = geoObj.getJsonArray("geonames");

            List<SitioTuristico> sitios = new ArrayList<>();

            if (!geonamesArray.isEmpty()) {
                String wikipediaTitle = geonamesArray.getJsonObject(0).getString("title").replace(" ", "_");
                SitioTuristico sitio = obtenerInfoDesdeDBPedia(wikipediaTitle);
                sitios.add(sitio);
            }

            return sitios;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
