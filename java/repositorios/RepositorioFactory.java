package repositorios;

import javax.persistence.EntityManager;

import external.JPAUtil;

public class RepositorioFactory {

    private static String cadenaConexion = "tuCadenaDeConexion";
    private static String nombreBaseDatos = "tuBaseDeDatos";

    public static RepositorioEstacion crearRepositorioEstacion() {
        return new RepositorioEstacionMongoDB(cadenaConexion, nombreBaseDatos);
    }
    
    public static RepositorioBicicleta crearRepositorioBicicleta() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        return new RepositorioBicicletaJPA(entityManager);
    }


    // Método para crear una instancia del RepositorioHistoricoEstacionamiento
    public static RepositorioHistoricoEstacionamiento crearRepositorioHistoricoEstacionamiento() {
        // Aquí debes retornar una instancia de tu RepositorioHistoricoEstacionamiento
        // Por ejemplo:
        return new RepositorioHistoricoEstacionamientoMongoDB(cadenaConexion, nombreBaseDatos);
    }
    
    
    
    
}