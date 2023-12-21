package repositorios;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import modelo.HistoricoEstacionamiento;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class RepositorioHistoricoEstacionamientoMongoDB implements RepositorioHistoricoEstacionamiento {

    private MongoCollection<Document> collection;

    public RepositorioHistoricoEstacionamientoMongoDB(MongoDatabase database) {
        this.collection = database.getCollection("historicoEstacionamiento");
    }
    
    public RepositorioHistoricoEstacionamientoMongoDB(String cadenaConexion, String nombreBaseDatos) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public HistoricoEstacionamiento save(HistoricoEstacionamiento historico) {
        Document doc = HistoricoEstacionamiento.toDocument(historico);
        if (historico.getId() == null) {
            collection.insertOne(doc);
            historico.setId(doc.getObjectId("_id"));
        } else {
            collection.replaceOne(new Document("_id", historico.getId()), doc);
        }
        return historico;
    }

    @Override
    public HistoricoEstacionamiento findById(ObjectId id) {
        Document doc = collection.find(new Document("_id", id)).first();
        return doc != null ? HistoricoEstacionamiento.fromDocument(doc) : null;
    }

    @Override
    public List<HistoricoEstacionamiento> findAllByBicicletaCodigo(String bicicletaCodigo) {
        List<HistoricoEstacionamiento> historicos = new ArrayList<>();
        for (Document doc : collection.find(new Document("bicicletaCodigo", bicicletaCodigo))) {
            historicos.add(HistoricoEstacionamiento.fromDocument(doc));
        }
        return historicos;
    }

    @Override
    public void delete(HistoricoEstacionamiento historico) {
        collection.deleteOne(new Document("_id", historico.getId()));
    }
    
    //En lugar de recuperar todos los históricos de la bicicleta 
    //(que pueden llegar a ser muchos) e iterar sobre ellos, 
    //se podría filtar en la consulta mongodb por aquellos que no tengan fecha de fin.
    
    
    public List<HistoricoEstacionamiento> findActivosPorBicicleta(ObjectId bicicletaId) {
        List<HistoricoEstacionamiento> historicosActivos = new ArrayList<>();

        collection.find(
            Filters.and(
                Filters.eq("bicicletaId", bicicletaId),
                Filters.or(Filters.eq("fechaFin", null), Filters.exists("fechaFin", false))
            )
        ).forEach(doc -> historicosActivos.add(HistoricoEstacionamiento.fromDocument(doc)));

        return historicosActivos;
    }
    
    
   // Este método findActivosPorBicicleta utiliza un filtro que combina dos condiciones:

    //	Que el bicicletaId coincida con el ID proporcionado.
    //	Que el campo fechaFin sea null o no exista (para historicos que aún no han finalizado).
    
    
}
