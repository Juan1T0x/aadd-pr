package pruebas;

import modelo.Bicicleta;
import modelo.Incidencia;
import repositorios.RepositorioBicicletaJPA;
import repositorios.RepositorioIncidenciaJPA;
import servicios.ServicioIncidencias;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class JPATest {

    public static void main(String[] args) {
        // Crear una instancia de EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("journals");
        EntityManager em = emf.createEntityManager();

        // Crear instancias de repositorios
        RepositorioBicicletaJPA repoBicicleta = new RepositorioBicicletaJPA();
        RepositorioIncidenciaJPA repoIncidencia = new RepositorioIncidenciaJPA();

        // Inyectar EntityManager en repositorios
        repoBicicleta.setEntityManager(em);
        repoIncidencia.setEntityManager(em);

        // Crear una instancia de ServicioIncidencias
        ServicioIncidencias servicioIncidencias = new ServicioIncidencias(repoIncidencia, repoBicicleta);

        // Comenzar una transacción
        em.getTransaction().begin();

        try {
            // Ejemplo de operaciones
            // 1. Crear y guardar una nueva bicicleta
            Bicicleta nuevaBici = new Bicicleta("123ABC", "ModeloX");
            repoBicicleta.save(nuevaBici);

            // 2. Crear una incidencia
            String codigoIncidencia = servicioIncidencias.crearIncidencia(nuevaBici.getCodigo(), "Incidencia de prueba");

            // 3. Recuperar y mostrar todas las incidencias
            List<Incidencia> incidencias = repoIncidencia.findAll();
            for (Incidencia inc : incidencias) {
                System.out.println("Incidencia: " + inc.getDescripcion());
            }

            // 4. Actualizar estado de una incidencia
            servicioIncidencias.gestionarIncidencia(codigoIncidencia, "resuelta", "Solucionado", null);

            // Confirmar la transacción
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            // Cerrar EntityManager y EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}

