package repositorios;

import modelo.Incidencia;
import java.util.List;

import javax.persistence.EntityManager;

public interface RepositorioIncidencia {
    Incidencia save(Incidencia incidencia);
    Incidencia findById(Long id);
    List<Incidencia> findAll();
    void delete(Incidencia incidencia);
    void setEntityManager(EntityManager entityManager);
	boolean tieneIncidenciasAbiertas(String codigoBicicleta);
}