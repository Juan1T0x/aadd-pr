package modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bicicletas")
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String modelo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Column(name = "motivo_baja")
    private String motivoBaja;
    
    private Estacion estacion;

    // Constructores
    public Bicicleta() {
    }

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
    
    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

	public Object getEstacionActual() {
		return this.estacion;
	}
}

        
    
    
    
    
 