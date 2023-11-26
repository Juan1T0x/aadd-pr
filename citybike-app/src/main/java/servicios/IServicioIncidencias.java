package servicios;

import java.util.List;

import modelo.Incidencia;

public interface IServicioIncidencias {
    String crearIncidencia(String idBicicleta, String descripcion);
    void gestionarIncidencia(String idIncidencia, String estado, String motivoCierre);
    List<Incidencia> recuperarIncidenciasAbiertas();
}

