package repositorios;

import modelo.Bicicleta;
import modelo.HistoricoEstacionamiento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import external.EntityManagerHelper;

import java.util.List;

public class RepositorioBicicletaJPA implements RepositorioBicicleta {

	public RepositorioBicicletaJPA() {
        this.entityManager = EntityManagerHelper.getEntityManager();
    }
    @PersistenceContext
    private EntityManager entityManager;
    
    // Constructor que acepta un EntityManager
    public RepositorioBicicletaJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
	
	  //Cuando dais de alta la bicicleta, no creáis histórico y supuestamente se estaciona, pero al estacionarla la añadís a 
    //la colección de la estación, que no se persiste, y no actualizáis el campo estación de la bici, 
    //que de todas formas se guarda mal. Luego, al retirar la bici ya ignoráis la colección de bicis que hay en estación.
    
	
    public Bicicleta darAltaBicicleta(Bicicleta bicicleta, RepositorioHistoricoEstacionamiento repositorioHistorico) {
        // Guardar bicicleta
        save(bicicleta);
        
        // Crear y guardar histórico
        HistoricoEstacionamiento historico = new HistoricoEstacionamiento(/* parámetros necesarios */);
        repositorioHistorico.save(historico);
        
        return bicicleta;
    }
	
	
	
}
