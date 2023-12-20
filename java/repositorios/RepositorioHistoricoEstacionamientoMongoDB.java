package repositorios;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
}
