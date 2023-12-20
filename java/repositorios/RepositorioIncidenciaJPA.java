package repositorios;

import modelo.Incidencia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RepositorioIncidenciaJPA implements RepositorioIncidencia {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Incidencia save(Incidencia incidencia) {
        if (incidencia.getId() == null) {
            entityManager.persist(incidencia);
        } else {
            incidencia = entityManager.merge(incidencia);
        }
        return incidencia;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public Incidencia findById(Long id) {
        return entityManager.find(Incidencia.class, id);
    }

    @Override
    public List<Incidencia> findAll() {
        return entityManager.createQuery("from Incidencia", Incidencia.class).getResultList();
    }

    @Override
    public void delete(Incidencia incidencia) {
        entityManager.remove(incidencia);
    }
}
