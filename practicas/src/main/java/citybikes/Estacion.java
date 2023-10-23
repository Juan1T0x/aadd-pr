package citybikes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Estacion {
    private String nombre;
    private Date fechaAlta;
    private int numeroDePuestos;
    private String direccion;
    private String coordenadasGeograficas;  // Podrías usar una clase separada para esto, pero lo dejaremos como String para simplicidad.
    private String informacionTuristica;
    private List<Bici> bicis;
	private String id;
    private static List<Estacion> todasLasEstaciones = new ArrayList<>();
    private List<String> sitiosTuristicos = new ArrayList<>();
    
  
//Constructor de Estacion
    public Estacion(String nombre, int numeroDePuestos, String direccion, String coordenadasGeograficas) {
        this.id = UUID.randomUUID().toString();  // generamos un ID único
        this.fechaAlta = new Date(System.currentTimeMillis());  // establecemos la fecha actual
        this.nombre = nombre;
        this.numeroDePuestos = numeroDePuestos;
        this.direccion = direccion;
        this.coordenadasGeograficas = coordenadasGeograficas;
        this.bicis = new ArrayList<>();
    }
    
   
    
    public static List<Estacion> getTodasLasEstaciones() {
        return todasLasEstaciones;
    }

    
    public void agregarBici(Bici bici) {
        bicis.add(bici);
    }
    
    public void removerBici(Bici bici) {
        bicis.remove(bici);
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Bici> getBicis() {
		return bicis;
	}

	public void setBicis(List<Bici> bicis) {
		this.bicis = bicis;
	}
    
	  public Date getFechaAlta() {
			return fechaAlta;
		}

		public void setFechaAlta(Date fechaAlta) {
			this.fechaAlta = fechaAlta;
		}


		public int getNumeroDePuestos() {
			return numeroDePuestos;
		}


		public void setNumeroDePuestos(int numeroDePuestos) {
			this.numeroDePuestos = numeroDePuestos;
		}

		public String getDireccion() {
			return direccion;
		}


		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getCoordenadasGeograficas() {
			return coordenadasGeograficas;
		}


		public void setCoordenadasGeograficas(String coordenadasGeograficas) {
			this.coordenadasGeograficas = coordenadasGeograficas;
		}


		public String getInformacionTuristica() {
			return informacionTuristica;
		}


		public void setInformacionTuristica(String informacionTuristica) {
			this.informacionTuristica = informacionTuristica;
		}

		public static void setTodasLasEstaciones(List<Estacion> todasLasEstaciones) {
			Estacion.todasLasEstaciones = todasLasEstaciones;
		}

		public List<String> getSitiosTuristicos() {
		    return sitiosTuristicos;
		}

		public void setSitiosTuristicos(List<String> sitiosTuristicos) {
		    this.sitiosTuristicos = sitiosTuristicos;
		}

		public String getId() {
		    return id;
		}



		@Override
		public String toString() {
			return "Estacion [nombre=" + nombre + ", fechaAlta=" + fechaAlta + ", numeroDePuestos=" + numeroDePuestos
					+ ", direccion=" + direccion + ", coordenadasGeograficas=" + coordenadasGeograficas
					+ ", informacionTuristica=" + informacionTuristica + ", bicis=" + bicis + ", id=" + id
					+ ", sitiosTuristicos=" + sitiosTuristicos + "]";
		}



}