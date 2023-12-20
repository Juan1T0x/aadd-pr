package repositorios;

import modelo.Bicicleta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RepositorioBicicletaJPA implements RepositorioBicicleta {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Bicicleta save(Bicicleta bicicleta) {
        entityManager.persist(bicicleta);
        return bicicleta;
    }
    
    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    
    @Override
    public Bicicleta findById(Long id) {
        return entityManager.find(Bicicleta.class, id);
    }

    @Override
    public List<Bicicleta> findAll() {
        return entityManager.createQuery("from Bicicleta", Bicicleta.class).getResultList();
    }

    @Override
    public void delete(Bicicleta bicicleta) {
        entityManager.remove(bicicleta);
    }

	@Override
	public Bicicleta findById(String codigoBicicleta) {
		// TODO Auto-generated method stub
		return null;
	}
}
