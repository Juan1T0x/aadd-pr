package repositorios;

import modelo.HistoricoEstacionamiento;
import org.bson.types.ObjectId;
import java.util.List;

public interface RepositorioHistoricoEstacionamiento {
    HistoricoEstacionamiento save(HistoricoEstacionamiento historico);
    HistoricoEstacionamiento findById(ObjectId id);
    List<HistoricoEstacionamiento> findAllByBicicletaCodigo(String bicicletaCodigo);
    void delete(HistoricoEstacionamiento historico);
}

