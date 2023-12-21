package repositorios;

import modelo.Incidencia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    @Override
    public boolean tieneIncidenciasAbiertas(String codigoBicicleta) {
    	
    	//Recuperar incidencias abiertas podría ser una consulta JPQL.
        String jpql = "SELECT COUNT(i) FROM Incidencia i WHERE i.bicicleta.codigo = :codigoBicicleta AND i.estado = 'abierta'";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("codigoBicicleta", codigoBicicleta);
        long count = query.getSingleResult();
        return count > 0;
    }
    
}
