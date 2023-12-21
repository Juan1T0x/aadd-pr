package pruebas;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import antlr.collections.List;
import modelo.Estacion;
import modelo.HistoricoEstacionamiento;
import repositorios.RepositorioBicicleta;
import repositorios.RepositorioBicicletaJPA;
import repositorios.RepositorioEstacionMongoDB;
import repositorios.RepositorioHistoricoEstacionamientoMongoDB;
import servicios.ServicioEstaciones;
import servicios.ServicioException;

public class MongoDBTest {

    public static void main(String[] args) throws ServicioException {
        // Cadena de conexión a MongoDB
        String cadenaConexion = "mongodb+srv://joseangelgarciapagan8:0eMpNiLOb2Y55Eub@joseangelgp.byffz2l.mongodb.net/?retryWrites=true&w=majority";

        // Conexión a MongoDB
        MongoClient mongoClient = MongoClients.create(cadenaConexion);
        MongoDatabase database = mongoClient.getDatabase("Ubicacion"); // Reemplaza "nombreBaseDatos" con el nombre real de tu base de datos

        // Instanciación de los repositorios
        RepositorioEstacionMongoDB repositorioEstacion = new RepositorioEstacionMongoDB(database);
        RepositorioHistoricoEstacionamientoMongoDB repositorioHistorico = new RepositorioHistoricoEstacionamientoMongoDB(database);

        // Realizar operaciones de prueba
        realizarOperacionesDePrueba(repositorioEstacion, repositorioHistorico);
  
        // Crear una estación
        Estacion estacion = new Estacion("Estacion de Prueba", 40.7128, -74.0060, 10, "Dirección de Prueba", "Información de Prueba");
        estacion = repositorioEstacion.save(estacion);
        System.out.println("Estación creada: " + estacion.getId());

        // Crear un registro histórico de estacionamiento
        HistoricoEstacionamiento historico = new HistoricoEstacionamiento("Bicicleta001", estacion.getId(), new Date(0));
        historico = repositorioHistorico.save(historico);
        System.out.println("Registro histórico de estacionamiento creado: " + historico.getId());

        // Buscar una estación por su ID
        ObjectId estacionId = estacion.getId();
        Estacion estacionEncontrada = repositorioEstacion.findById(estacionId);
        if (estacionEncontrada != null) {
            System.out.println("Estación encontrada: " + estacionEncontrada.getNombre());
        } else {
            System.out.println("Estación no encontrada.");
        }
        
        
     // Crear una nueva estación
        Estacion nuevaEstacion = new Estacion("Estación de Prueba", 42.123, -71.456, 20, "Dirección de Prueba", "Información de Prueba");

        // Guardar la estación en la base de datos
        repositorioEstacion.save(nuevaEstacion);

        // Buscar la estación por ID y verificar si se insertó correctamente
        Estacion estacionInsertada = repositorioEstacion.findById(nuevaEstacion.getId());
        System.out.println("Estación insertada: " + estacionInsertada.getNombre());

      
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tuUnidadDePersistencia");
        EntityManager em = emf.createEntityManager();
        
        //ServicioEstaciones servicioEstaciones = new ServicioEstaciones(repositorioBici, repositorioEstacion, repositorioHistorico);
        RepositorioBicicleta repositorioBicicleta = new RepositorioBicicletaJPA(em); // Reemplaza con la implementación real
        
        ServicioEstaciones servicioEstaciones = new ServicioEstaciones(repositorioBicicleta, repositorioEstacion, repositorioHistorico);
        
     // Crear una nueva bicicleta
        String modeloBicicleta = "Bicicleta de Prueba";
		String codigoBicicleta = servicioEstaciones.altaBicicleta(modeloBicicleta, estacionInsertada.getId().toString());
        System.out.println("Bicicleta insertada con código: " + codigoBicicleta);

        // Estacionar la bicicleta
        servicioEstaciones.estacionarBicicleta(codigoBicicleta, estacionInsertada.getId().toString());
        System.out.println("Bicicleta estacionada en " + estacionInsertada.getNombre());

        // Retirar la bicicleta
        servicioEstaciones.retirarBicicleta(codigoBicicleta);
        System.out.println("Bicicleta retirada");

        
        
        
    }
    



    private static void realizarOperacionesDePrueba(RepositorioEstacionMongoDB repositorioEstacion, RepositorioHistoricoEstacionamientoMongoDB repositorioHistorico) {
   
    }
}
