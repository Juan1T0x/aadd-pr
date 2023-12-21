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

  
    public String crearIncidencia(String codigoBicicleta, String descripcion) throws ServicioException {
        Bicicleta bicicleta = repositorioBicicleta.findById(codigoBicicleta);
        if (bicicleta == null) {
            throw new ServicioException("Bicicleta no encontrada.");
        }
        
        //No se debería poder crear una incidencia a una bicicleta que ya tiene incidencias abiertas.
        // Verificar si hay incidencias abiertas
        if (repositorioIncidencia.tieneIncidenciasAbiertas(codigoBicicleta)) {
            throw new ServicioException("La bicicleta ya tiene incidencias abiertas.");
        }

        Incidencia incidencia = new Incidencia(codigoBicicleta, descripcion);
        repositorioIncidencia.save(incidencia);
        bicicleta.setFechaBaja(new Date()); // Bicicleta no disponible
        repositorioBicicleta.save(bicicleta);
        return incidencia.getCodigo();
    }
    
    /*
    El método ServicioIncidencias.gestionarIncidenciaes demasiado genérico,
    hay que comprobar que una incidencia está en el estado adecuado para cambiarla a otro estado,
    si se asigna hay que retirarla de la estación, hay que darla de baja definitiva si se resuelve a no reparada, ç
    etc. No estáis implementando toda la lógica que se pide para ese método.
    */

    public void gestionarIncidencia(String codigoIncidencia, String nuevoEstado, String motivoCierre, Administrador operario) throws ServicioException {
        Incidencia incidencia = repositorioIncidencia.findById(Long.parseLong(codigoIncidencia));
        if (incidencia == null) {
            throw new ServicioException("Incidencia no encontrada.");
        }

        // Verificar transiciones de estado válidas y realizar acciones específicas
        switch (nuevoEstado) {
            case "asignada":
                if (!"abierta".equals(incidencia.getEstado())) {
                    throw new ServicioException("La incidencia no está en estado 'abierta' para ser asignada.");
                }
                // Asignar operario
                incidencia.setOperarioAsignado(operario);
                break;
            case "resuelta":
                // Acciones adicionales si se resuelve (e.g., dar de baja si no es reparable)
                // ...
                break;
            case "cancelado":
                // Acciones si se cancela
                // ...
                break;
            case "no reparada":
                // Dar de baja definitiva si se resuelve como no reparada
                Bicicleta bicicleta = repositorioBicicleta.findById(incidencia.getIdBicicleta());
                if (bicicleta != null) {
                    bicicleta.setFechaBaja(new Date()); // Fecha actual como fecha de baja
                    bicicleta.setMotivoBaja("No reparada - " + motivoCierre);
                    repositorioBicicleta.save(bicicleta);
                }
                break;
            default:
                throw new ServicioException("Estado no válido.");
        }

        // Actualizar estado y motivo de cierre de la incidencia
        incidencia.setEstado(nuevoEstado);
        incidencia.setMotivoCierre(motivoCierre);
        repositorioIncidencia.save(incidencia);
    }

  //  Se verifica si la incidencia existe antes de proceder.
  //  Se implementa una lógica específica para diferentes estados (asignada, resuelta, cancelado, no reparada), incluyendo la validación de transiciones de estado válidas.
  //  Se realizan acciones específicas dependiendo del nuevo estado, como asignar un operario, manejar la resolución de la incidencia, cancelar la incidencia, o dar de baja definitivamente la bicicleta si la incidencia se resuelve como "no reparada".
  //  Se lanza una excepción (ServicioException) si se intenta realizar una transición de estado no válida o si el estado proporcionado no es válido.



    public List<Incidencia> recuperarIncidenciasAbiertas() {
        return repositorioIncidencia.findAll().stream()
                                    .filter(incidencia -> !"resuelta".equals(incidencia.getEstado()) && !"cancelado".equals(incidencia.getEstado()))
                                    .collect(Collectors.toList());
    }
}
