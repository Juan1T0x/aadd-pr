package repositorios;

import modelo.Estacion;
import java.util.List;

import org.bson.types.ObjectId;

public interface RepositorioEstacion {
    Estacion save(Estacion estacion);
    Estacion findById(ObjectId id);
    List<Estacion> findAll();
    void delete(Estacion estacion);
    void createGeoIndex();
    List<Estacion> findNearby(double latitud, double longitud, double maxDistance);
}
