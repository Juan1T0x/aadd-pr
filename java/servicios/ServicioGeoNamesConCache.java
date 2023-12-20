package servicios;

import java.util.List;
import external.ServicioGeoNamesImpl;
import modelo.SitioTuristico;
import repositorios.RepositorioJSON;

public class ServicioGeoNamesConCache {

    private ServicioGeoNamesImpl servicioGeoNames = ServicioGeoNamesImpl.getInstance();
    private RepositorioJSON repositorioJson = new RepositorioJSON() {}; // Si RepositorioJSON es abstracta

    public List<SitioTuristico> obtenerSitioTuristicoInteres(double latitud, double longitud) {
        String cacheFileName = "geoNames_" + latitud + "_" + longitud + ".json";

        // Intenta cargar desde el caché
        List<SitioTuristico> sitiosTuristicos = repositorioJson.load(cacheFileName, List.class);
        if (sitiosTuristicos != null) {
            return sitiosTuristicos;
        }

        // Si no hay caché, utiliza el servicio externo y guarda el resultado
        sitiosTuristicos = servicioGeoNames.obtenerSitioTuristicoInteres(latitud, longitud);
        repositorioJson.save(sitiosTuristicos, cacheFileName);

        return sitiosTuristicos;
    }
}
