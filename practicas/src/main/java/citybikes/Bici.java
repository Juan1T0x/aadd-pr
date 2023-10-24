package citybikes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Bici {
    private String codigo;
    private String modelo;
    private Date fechaAlta;
    private Date fechaBaja;
    private String motivoBaja;
    private List<String> historicoUbicaciones = new ArrayList<>();
    boolean isAlquilada;
    private Estacion estacion;
    private List<Incidencia> incidencias = new ArrayList<>();
    
    
    //Constroctur de Bici
    public Bici(String codigo) {
        this.codigo = codigo;
        this.isAlquilada = false;
        this.fechaBaja = null;
        this.motivoBaja = null;
        this.estacion = null;
    }
    


    public void agregarIncidencia(Incidencia incidencia) {
        this.incidencias.add(incidencia);
    }

    public List<Incidencia> obtenerIncidencias() {
        return incidencias;
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

	public boolean isAlquilada() {
		return isAlquilada;
	}

	public void setAlquilada(boolean isAlquilada) {
		this.isAlquilada = isAlquilada;
	}

	public void alquilar() {
        this.isAlquilada = true;
    }

    public void devolver() {
        this.isAlquilada = false;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }
}
