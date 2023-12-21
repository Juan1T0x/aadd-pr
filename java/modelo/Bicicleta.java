package modelo;

import javax.persistence.*;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bicicletas")
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Mejor etiquetar con @Lob los campos que pueden ser textos largos.
 
    @Lob
    @Column(nullable = false)
    private String codigo;
    
    @Lob
    @Column(nullable = false)
    private String modelo;

    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Lob
    @Column(name = "motivo_baja")
    private String motivoBaja;
    

    
    
    //La clase Bicicleta tiene un atributo de tipoEstacion, pero la estación con todos sus campos se guarda en mongodb, 
    //por lo que el atributo estacion en Bicicleta debería guardar el id de la estación, 
    //y tal como está definido, no se va a guardar bien en MySQL.
	// Cambio del tipo de 'Estacion' a 'String' o 'Long'
    @Column(name = "estacion_id")
    private ObjectId estacionId;


 
    
    // Constructores
    public Bicicleta() {
    }
    
    //No definís la asociación entre Bicicleta e Incidencia.
//Para definir la asociación entre Bicicleta e Incidencia en tu modelo Java, debes establecer relaciones en ambas clases. 
    
    @OneToMany(mappedBy = "bicicleta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidencias;
    
    public List<Incidencia> getIncidencias() {
        return incidencias;
    }
    
    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    

    public Bicicleta(String codigo, String modelo) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.fechaAlta = new Date();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

	public ObjectId getEstacionId() {
		return estacionId;
	}

    
	  public void setEstacionId(ObjectId estacionId) {
	        this.estacionId = estacionId;
	    }
	    
    
}

        
    
    
    
    
 