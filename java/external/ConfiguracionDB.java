package external;

import com.mongodb.client.MongoDatabase;

import repositorios.RepositorioEstacionMongoDB;

public class ConfiguracionDB {
    public static void inicializarIndices(MongoDatabase database) {
        RepositorioEstacionMongoDB repositorioEstacion = new RepositorioEstacionMongoDB(database);
        repositorioEstacion.createGeoIndex();
       
    }
}