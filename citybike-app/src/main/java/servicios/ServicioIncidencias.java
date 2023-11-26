package servicios;

import modelo.Administrador;
import modelo.Bicicleta;
import modelo.Incidencia;
import repositorios.RepositorioBicicleta;
import repositorios.RepositorioIncidencia;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioIncidencias {

    private RepositorioIncidencia repositorioIncidencia;
    private RepositorioBicicleta repositorioBicicleta;

    public ServicioIncidencias(RepositorioIncidencia repositorioIncidencia, RepositorioBicicleta repositorioBicicleta) {
        this.repositorioIncidencia = repositorioIncidencia;
        this.repositorioBicicleta = repositorioBicicleta;
    }

    public String crearIncidencia(String codigoBicicleta, String descripcion) {
        Bicicleta bicicleta = repositorioBicicleta.findById(codigoBicicleta);
        if (bicicleta != null) {
            Incidencia incidencia = new Incidencia(codigoBicicleta, descripcion);
            repositorioIncidencia.save(incidencia);
            bicicleta.setFechaBaja(new Date()); // Bicicleta no disponible
            repositorioBicicleta.save(bicicleta);
            return incidencia.getCodigo();
        }
        return null; // Bicicleta no encontrada
    }

    public void gestionarIncidencia(String codigoIncidencia, String estado, String motivoCierre, Administrador operario) {
        Incidencia incidencia = repositorioIncidencia.findById(Long.parseLong(codigoIncidencia));
        if (incidencia != null) {
            incidencia.setEstado(estado);
            incidencia.setMotivoCierre(motivoCierre);
            incidencia.setOperarioAsignado(operario);
            repositorioIncidencia.save(incidencia);

            if ("cancelado".equals(estado) || "resuelta".equals(estado)) {
                Bicicleta bicicleta = repositorioBicicleta.findById(incidencia.getIdBicicleta());
                if (bicicleta != null) {
                    bicicleta.setFechaBaja(null); // Bicicleta disponible si se resuelve o cancela
                    repositorioBicicleta.save(bicicleta);
                }
            }
        }
    }

    public List<Incidencia> recuperarIncidenciasAbiertas() {
        return repositorioIncidencia.findAll().stream()
                                    .filter(incidencia -> !"resuelta".equals(incidencia.getEstado()) && !"cancelado".equals(incidencia.getEstado()))
                                    .collect(Collectors.toList());
    }
}
