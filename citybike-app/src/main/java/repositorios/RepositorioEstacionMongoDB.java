package repositorios;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

import modelo.Estacion;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEstacionMongoDB implements RepositorioEstacion {

    private MongoCollection<Document> collection;

    public RepositorioEstacionMongoDB(MongoDatabase database) {
        this.collection = database.getCollection("estaciones");
    }

    @Override
    public Estacion save(Estacion estacion) {
        Document doc = Estacion.toDocument(estacion);
        if (estacion.getId() == null) {
            collection.insertOne(doc);
            estacion.setId(doc.getObjectId("_id"));
        } else {
            collection.replaceOne(new Document("_id", estacion.getId()), doc);
        }
        return estacion;
    }

    public void createGeoIndex() {
        collection.createIndex(Indexes.geo2dsphere("ubicacion"));
    }
    
    public List<Estacion> findNearby(double latitud, double longitud, double maxDistance) {
        List<Estacion> estacionesCercanas = new ArrayList<>();
        Point refPoint = new Point(new Position(longitud, latitud));

        collection.find(Filters.near("ubicacion", refPoint, maxDistance, 0.0))
                  .forEach(doc -> estacionesCercanas.add(Estacion.fromDocument(doc)));

        return estacionesCercanas;
    }

    
    @Override
    public Estacion findById(ObjectId id) {
        Document doc = collection.find(new Document("_id", id)).first();
        return doc != null ? Estacion.fromDocument(doc) : null;
    }

    @Override
    public List<Estacion> findAll() {
        List<Estacion> estaciones = new ArrayList<>();
        for (Document doc : collection.find()) {
            estaciones.add(Estacion.fromDocument(doc));
        }
        return estaciones;
    }

    @Override
    public void delete(Estacion estacion) {
        collection.deleteOne(new Document("_id", estacion.getId()));
    }
}
