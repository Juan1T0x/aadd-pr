package citybikes;

import java.util.Date;
import java.util.UUID;

public class Alquiler {
	
	private Date fechaInicio;
	private Date fechaFin;
	private String id;
	
	public Alquiler(Date fechaIni, Date fecaFin) {
		this.fechaInicio=fechaIni;
		this.fechaFin=null;
		this.id = UUID.randomUUID().toString();  // generamos un ID único
		
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Alquiler [fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", id=" + id + "]";
	}
	

}
