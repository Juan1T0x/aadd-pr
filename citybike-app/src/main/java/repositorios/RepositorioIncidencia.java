package repositorios;

import modelo.Incidencia;
import java.util.List;

public interface RepositorioIncidencia {
    Incidencia save(Incidencia incidencia);
    Incidencia findById(Long id);
    List<Incidencia> findAll();
    void delete(Incidencia incidencia);
}