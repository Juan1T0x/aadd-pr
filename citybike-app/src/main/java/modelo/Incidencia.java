package modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "incidencias")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(nullable = false)
    private String estado;
    
    private String motivocierre;
    private Administrador operario;
    private Bicicleta bici;

    // Constructores
    public Incidencia() {
        this.fechaCreacion = new Date();
        this.estado = "pendiente";
    }

    public Incidencia(String codigo, String descripcion) {
        this();
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaCreacion = new Date();
        this.estado = "pendiente";
        this.codigo = generateUniqueId();
    }
    

    private String generateUniqueId() {
		// TODO Auto-generated method stub
		return null;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	public void setMotivoCierre(String motivoCierre) {
		this.motivocierre=motivoCierre;
		
	}

	public void setOperarioAsignado(Administrador operario) {
		this.operario=operario;
		
	}

	public String getIdBicicleta() {
		return this.bici.getCodigo();
	}
	
	
}
