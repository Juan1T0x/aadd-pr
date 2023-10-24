package citybikes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Incidencia {
    private Date fechaCreacion;
    private String descripcion;
    private Estado estado;  // podría ser un Enum si se quiere limitar a estados específicos
    private Administrador operarioAsignado;
    private Bici bicicletaAfectada;

    // Lista estática que almacena todas las incidencias pendientes
    private static List<Incidencia> incidenciasPendientes = new ArrayList<>();
    
    
    
    // Constructor de Incidencia
    public Incidencia(String descripcion, Bici bicicletaAfectada) {
        this.fechaCreacion = new Date(0); // Fecha actual
        this.descripcion = descripcion;
        this.bicicletaAfectada = bicicletaAfectada;
        this.estado = Estado.PENDIENTE;
        this.operarioAsignado = null; // Inicialmente no hay operario asignado
        incidenciasPendientes.add(this);
    }

    
    // Método para obtener todas las incidencias pendientes
    public static List<Incidencia> getIncidenciasPendientes() {
        return incidenciasPendientes;
    }

 
    // Getters y Setters
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getOperarioAsignado() {
        return operarioAsignado;
    }

    public void setOperarioAsignado(Administrador operarioAsignado) {
        this.operarioAsignado = operarioAsignado;
    }

    public Bici getBicicletaAfectada() {
        return bicicletaAfectada;
    }

    public void setBicicletaAfectada(Bici bicicletaAfectada) {
        this.bicicletaAfectada = bicicletaAfectada;
    }
}