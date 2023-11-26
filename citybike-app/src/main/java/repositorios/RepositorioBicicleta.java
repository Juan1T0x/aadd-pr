package repositorios;

import modelo.Bicicleta;

import javax.persistence.EntityManager;
import java.util.List;

public interface RepositorioBicicleta {
    Bicicleta save(Bicicleta bicicleta);
    Bicicleta findById(String codigoBicicleta);
    List<Bicicleta> findAll();
    void delete(Bicicleta bicicleta);
	Bicicleta findById(Long id);
}
