package cache;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.spi.JsonbProvider;

import external.ServicioDBpediaImpl;
import modelo.SitioTuristicoCompleto;

public class RepositorioCacheImpl implements RepositorioCache {

	// DEPRECATED: antes usabamos esta lista para guardar los objetos pero si existe el json no tiene sentido mantenerla
	//private List<SitioTuristicoCompleto> sitiosTuristicosCompletos = new ArrayList<>();
	
	private final static String JSON_FILE = "json/cacheSitioTuristico.json";

	// TODO: Singleton

	private static RepositorioCacheImpl instance = new RepositorioCacheImpl();

	// Configuracion de JSON-B

	JsonbConfig config = new JsonbConfig().withNullValues(true).withFormatting(true)
			.withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
			.withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL);

	Jsonb contexto = JsonbProvider.provider().create().build();

	Jsonb jsonb = JsonbBuilder.create(config);
	
	private RepositorioCacheImpl() {
		
	}

	public static RepositorioCacheImpl getInstance() {
		return instance;
	}

	@Override
	public SitioTuristicoCompleto saveSitioTuristicoCompleto(SitioTuristicoCompleto sitioTuristicoCompleto) {
        List<SitioTuristicoCompleto> sitiosTuristicosCompletos = loadSitiosTuristicosCompletos();
        sitiosTuristicosCompletos.add(sitioTuristicoCompleto);
		// Guardar en el JSON
		try {
			FileWriter writer = new FileWriter(JSON_FILE);
			jsonb.toJson(sitiosTuristicosCompletos, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sitioTuristicoCompleto;
	}

	@Override
	public SitioTuristicoCompleto findSitioTuristicoCompletoById(String id) {
		// Recorrer el JSON
        List<SitioTuristicoCompleto> sitiosTuristicosCompletos = loadSitiosTuristicosCompletos();
		for (SitioTuristicoCompleto sitioTuristicoCompleto : sitiosTuristicosCompletos) {
			if (sitioTuristicoCompleto.getId().equals(id)) {
				return sitioTuristicoCompleto;
			}
		}
		return new SitioTuristicoCompleto();
	}
	@Override
	public List<SitioTuristicoCompleto> getAllSitiosTuristicosCompletos() {
		// Recorrer el JSON
        List<SitioTuristicoCompleto> sitiosTuristicosCompletos = loadSitiosTuristicosCompletos();
		return sitiosTuristicosCompletos;
	}
	
    // Método para cargar la lista de objetos desde el archivo JSON
    private List<SitioTuristicoCompleto> loadSitiosTuristicosCompletos() {
        try {
            FileReader reader = new FileReader(JSON_FILE);
            List<SitioTuristicoCompleto> sitiosTuristicosCompletos = jsonb.fromJson(reader, new ArrayList<SitioTuristicoCompleto>() {}.getClass().getGenericSuperclass());
            return sitiosTuristicosCompletos;
        } catch (IOException e) {
            // Si el archivo no existe, se devuelve una lista vacía
            return new ArrayList<>();
        }
    }

	public static void main(String[] args) {
		RepositorioCacheImpl repo = RepositorioCacheImpl.getInstance();
		SitioTuristicoCompleto sitio = repo.findSitioTuristicoCompletoById("Catedral_de_Murcia");
		System.out.println(sitio);
		//SitioTuristicoCompleto sitio = servicio.obtenerInformacionSitioTuristico("https://es.wikipedia.org/wiki/Catedral_de_Murcia");
		//SitioTuristicoCompleto sitio2 = servicio.obtenerInformacionSitioTuristico("https://es.wikipedia.org/wiki/Gran_Pirámide_de_Guiza");
		//repo.saveSitioTuristicoCompleto(sitio);
		//repo.saveSitioTuristicoCompleto(sitio2);
		
		
	}

}
