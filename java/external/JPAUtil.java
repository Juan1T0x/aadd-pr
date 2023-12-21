package external;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

public class JPAUtil {
    private static final EntityManagerFactory emFactory;

    static {
        // "miUnidadDePersistencia" debe coincidir con el nombre de tu unidad de persistencia en persistence.xml
        emFactory = Persistence.createEntityManagerFactory("miUnidadDePersistencia");
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static void close() {
        emFactory.close();
    }
}