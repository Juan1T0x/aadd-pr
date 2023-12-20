package modelo;

import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Estacion {
    private ObjectId id;
    private String nombre;
    private double latitud;
    private double longitud;
    private int numeroPuestos;
    private String direccion;
    private Date fechaAlta;
    private String informacion;
    private List<Bicicleta> bicicletas;
    private List<SitioTuristico> sitiosTuristicosEstablecidos;

    // Constructores
    public Estacion() {
    }

    public Estacion(String nombre, double latitud, double longitud, int numeroPuestos, String direccion, String informacion) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.numeroPuestos = numeroPuestos;
        this.direccion = direccion;
        this.fechaAlta = new Date();
        this.informacion = informacion;
    }

    // Getters y setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getNumeroPuestos() {
        return numeroPuestos;
    }

    public void setNumeroPuestos(int numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public List<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public void setBicicletas(List<Bicicleta> bicicletas) {
        this.bicicletas = bicicletas;
    }

    public List<SitioTuristico> getSitiosTuristicosEstablecidos() {
        return sitiosTuristicosEstablecidos;
    }

    public void setSitiosTuristicosEstablecidos(List<SitioTuristico> sitiosTuristicosEstablecidos) {
        this.sitiosTuristicosEstablecidos = sitiosTuristicosEstablecidos;
    }

    public static Document toDocument(Estacion estacion) {
        Document doc = new Document();
        doc.append("nombre", estacion.getNombre())
           .append("ubicacion", new Document("type", "Point").append("coordinates", List.of(estacion.getLongitud(), estacion.getLatitud())))
           .append("numeroPuestos", estacion.getNumeroPuestos())
           .append("direccion", estacion.getDireccion())
           .append("fechaAlta", estacion.getFechaAlta())
           .append("informacion", estacion.getInformacion());
        // Agregar otros campos necesarios
        return doc;
    }

    // Convertir de Document a Estacion
    public static Estacion fromDocument(Document doc) {
        Estacion estacion = new Estacion();
        estacion.setId(doc.getObjectId("_id"));
        estacion.setNombre(doc.getString("nombre"));
        List<Double> coordinates = doc.get("ubicacion", Document.class).getList("coordinates", Double.class);
        estacion.setLatitud(coordinates.get(1));
        estacion.setLongitud(coordinates.get(0));
        estacion.setNumeroPuestos(doc.getInteger("numeroPuestos"));
        estacion.setDireccion(doc.getString("direccion"));
        estacion.setFechaAlta(doc.getDate("fechaAlta"));
        estacion.setInformacion(doc.getString("informacion"));
        // Agregar otros campos necesarios
        return estacion;
    }
}
