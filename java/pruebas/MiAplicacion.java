package pruebas;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import modelo.Estacion;
import modelo.HistoricoEstacionamiento;

import org.bson.types.ObjectId;
import repositorios.RepositorioEstacionMongoDB;
import repositorios.RepositorioHistoricoEstacionamientoMongoDB;
import external.ConfiguracionDB;

import java.util.Date;

public class MiAplicacion {
    public static void main(String[] args) {
        // Cadena de conexión a MongoDB
        String cadenaConexion = "mongodb+srv://tuUsuario:tuContraseña@tuHost/tuBaseDeDatos";

        // Conexión a MongoDB
        try (MongoClient mongoClient = MongoClients.create(cadenaConexion)) {
            MongoDatabase database = mongoClient.getDatabase("Ubicacion");

            // Inicializar índices
            ConfiguracionDB.inicializarIndices(database);

            // Instanciación de los repositorios
            RepositorioEstacionMongoDB repositorioEstacion = new RepositorioEstacionMongoDB(database);
            RepositorioHistoricoEstacionamientoMongoDB repositorioHistorico = new RepositorioHistoricoEstacionamientoMongoDB(database);

            // Operaciones de prueba
            realizarOperacionesDePrueba(repositorioEstacion, repositorioHistorico);
        }
    }

    private static void realizarOperacionesDePrueba(RepositorioEstacionMongoDB repositorioEstacion, 
                                                    RepositorioHistoricoEstacionamientoMongoDB repositorioHistorico) {
        // Crear y guardar una nueva estación
        Estacion nuevaEstacion = new Estacion("Estación Central", 40.7128, -74.0060, 15, "Calle Principal 123", "Información de la Estación Central");
        nuevaEstacion = repositorioEstacion.save(nuevaEstacion);
        System.out.println("Nueva estación creada: " + nuevaEstacion.getId());

        // Buscar la estación por su ID
        ObjectId estacionId = nuevaEstacion.getId();
        Estacion estacionEncontrada = repositorioEstacion.findById(estacionId);
        if (estacionEncontrada != null) {
            System.out.println("Estación encontrada: " + estacionEncontrada.getNombre());
        } else {
            System.out.println("Estación no encontrada.");
        }

        // Crear un registro histórico de estacionamiento
        // Supongamos que tenemos un ID de bicicleta
        ObjectId bicicletaId = new ObjectId(); // Reemplazar con un ID real si es necesario
        HistoricoEstacionamiento historico = new HistoricoEstacionamiento(bicicletaId.toString(), estacionId, new Date());
        historico = repositorioHistorico.save(historico);
        System.out.println("Registro histórico de estacionamiento creado: " + historico.getId());
    }
}
