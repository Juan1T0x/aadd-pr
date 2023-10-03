package jaxb;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "acta")
public class Acta {
    private String convocatoria;
    private int curso;
    private int asignatura;
    private List<Calificacion> calificaciones;
    private List<Diligencia> diligencias;

    // Getters and Setters

    @XmlAttribute(name = "convocatoria")
    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    @XmlAttribute(name = "curso")
    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    @XmlAttribute(name = "asignatura")
    public int getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(int asignatura) {
        this.asignatura = asignatura;
    }

    @XmlElement(name = "calificacion")
    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    @XmlElement(name = "diligencia")
    public List<Diligencia> getDiligencias() {
        return diligencias;
    }

    public void setDiligencias(List<Diligencia> diligencias) {
        this.diligencias = diligencias;
    }
    public double calcularNotaMedia() {
        double totalNotas = 0;
        int cantidadCalificaciones = calificaciones.size();

        for (Calificacion calificacion : calificaciones) {
            totalNotas += calificacion.getNota();
        }

        return cantidadCalificaciones > 0 ? totalNotas / cantidadCalificaciones : 0;
    }
    
    public int contarDiligenciasUltimoMes() {
        int count = 0;

        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Iterar sobre las diligencias
        for (Diligencia diligencia : diligencias) {
            try {
                // Parsear la fecha de la diligencia
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaDiligencia = dateFormat.parse(diligencia.getFecha());

                // Comparar si la diligencia es del último mes
                if (isLastMonth(fechaActual, fechaDiligencia)) {
                    count++;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return count;
    }
    private boolean isLastMonth(Date fechaActual, Date fechaDiligencia) {
        // Obtener el mes actual y el mes de la diligencia
        @SuppressWarnings("deprecation")
		int mesActual = fechaActual.getMonth();
        @SuppressWarnings("deprecation")
		int mesDiligencia = fechaDiligencia.getMonth();

        // La fecha de Java utiliza 0 para enero, 1 para febrero, y así sucesivamente
        // Por lo tanto, el último mes es mesActual == mesDiligencia
        return (mesActual == mesDiligencia);
    }
    
    public void incrementarNotas(double incremento) {
        for (Calificacion calificacion : calificaciones) {
            // Incrementar la nota en 0.5 puntos, asegurando que no supere 10
            calificacion.setNota(Math.min(calificacion.getNota() + incremento, 10));
        }
    }
}

class Calificacion {
    private String nif;
    private String nombre;
    private double nota;

    // Getters and Setters

    @XmlElement(name = "nif")
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "nota")
    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}

class Diligencia {
    private String nif;
    private int nota;
    private String fecha;
    private String extraordinaria;

    // Getters and Setters

    @XmlElement(name = "nif")
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @XmlElement(name = "nota")
    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @XmlAttribute(name = "fecha")
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @XmlAttribute(name = "extraordinaria")
    public String getExtraordinaria() {
        return extraordinaria;
    }

    public void setExtraordinaria(String extraordinaria) {
        this.extraordinaria = extraordinaria;
    }
}

