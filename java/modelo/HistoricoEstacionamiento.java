package modelo;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

public class HistoricoEstacionamiento {
    private ObjectId id;
    private String bicicletaCodigo;
    private ObjectId estacionId;
    private Date fechaInicio;
    private Date fechaFin;

    // Constructores
    public HistoricoEstacionamiento() {
    }

    public HistoricoEstacionamiento(String bicicletaCodigo, ObjectId estacionId, Date fechaInicio) {
        this.bicicletaCodigo = bicicletaCodigo;
        this.estacionId = estacionId;
        this.fechaInicio = fechaInicio;
        // La fecha de fin se establecerá cuando la bicicleta sea retirada de la estación
    }

    // Getters y setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getBicicletaCodigo() {
        return bicicletaCodigo;
    }

    public void setBicicletaCodigo(String bicicletaCodigo) {
        this.bicicletaCodigo = bicicletaCodigo;
    }

    public ObjectId getEstacionId() {
        return estacionId;
    }

    public void setEstacionId(ObjectId estacionId) {
        this.estacionId = estacionId;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    // Métodos de conversión Document
    public static Document toDocument(HistoricoEstacionamiento historico) {
        Document doc = new Document();
        doc.append("bicicletaCodigo", historico.getBicicletaCodigo())
           .append("estacionId", historico.getEstacionId())
           .append("fechaInicio", historico.getFechaInicio())
           .append("fechaFin", historico.getFechaFin());
        return doc;
    }

    public static HistoricoEstacionamiento fromDocument(Document doc) {
        HistoricoEstacionamiento historico = new HistoricoEstacionamiento();
        historico.setId(doc.getObjectId("_id"));
        historico.setBicicletaCodigo(doc.getString("bicicletaCodigo"));
        historico.setEstacionId(doc.getObjectId("estacionId"));
        historico.setFechaInicio(doc.getDate("fechaInicio"));
        historico.setFechaFin(doc.getDate("fechaFin"));
        return historico;
    }
}
