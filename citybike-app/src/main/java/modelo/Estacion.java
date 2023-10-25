package modelo;

import java.util.Date;
import java.util.List;

public class Estacion {

	// Propiedades

	private String id;
	private String nombre;
	private Date fechaAlta;
	private int numeroPuestos;
	private String direccion;
	private double latidud, longitud;
	private String informacion;

	private List<SitioTuristico> sitiosTuristicosEstablecidos;

	// Constructor

	public Estacion(String id, String nombre, Date fechaAlta, int numeroPuestos, String direccion, double latidud,
			double longitud, String informacion, List<SitioTuristico> sitiosTuristicosEstablecidos) {
		this.id = id;
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
		this.numeroPuestos = numeroPuestos;
		this.direccion = direccion;
		this.latidud = latidud;
		this.longitud = longitud;
		this.informacion = informacion;
		this.sitiosTuristicosEstablecidos = sitiosTuristicosEstablecidos;
	}

	public Estacion(String id, String nombre, int numeroPuestos, String direccion, double latitud, double longitud,
			String informacion) {

		this(id, nombre, new Date(), numeroPuestos, direccion, latitud, longitud, informacion, null);

	}

	public Estacion(String id, String nombre, int numeroPuestos, String direccion, double latitud, double longitud) {

		this(id, nombre, new Date(), numeroPuestos, direccion, latitud, longitud, null, null);

	}

	// Getters y setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getnPuestos() {
		return numeroPuestos;
	}

	public void setnPuestos(int numeroPuestos) {
		this.numeroPuestos = numeroPuestos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getLatidud() {
		return latidud;
	}

	public void setLatidud(double latidud) {
		this.latidud = latidud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public int getNumeroPuestos() {
		return numeroPuestos;
	}

	public void setNumeroPuestos(int numeroPuestos) {
		this.numeroPuestos = numeroPuestos;
	}

	public List<SitioTuristico> getSitiosTuristicosEstablecidos() {
		return sitiosTuristicosEstablecidos;
	}

	public void setSitiosTuristicosEstablecidos(List<SitioTuristico> sitiosTuristicosEstablecidos) {
		this.sitiosTuristicosEstablecidos = sitiosTuristicosEstablecidos;
	}

	@Override
	public String toString() {
		return "Estacion [id=" + id + ", nombre=" + nombre + ", fechaAlta=" + fechaAlta + ", numeroPuestos="
				+ numeroPuestos + ", direccion=" + direccion + ", latidud=" + latidud + ", longitud=" + longitud
				+ ", informacion=" + informacion + ", sitiosTuristicosEstablecidos=" + sitiosTuristicosEstablecidos
				+ "]";
	}	
	
}
