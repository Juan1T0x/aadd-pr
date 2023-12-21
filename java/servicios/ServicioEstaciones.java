package servicios;

import modelo.Bicicleta;
import modelo.Estacion;
import modelo.HistoricoEstacionamiento;
import modelo.SitioTuristico;
import repositorios.RepositorioBicicleta;
import repositorios.RepositorioEstacion;
import repositorios.RepositorioHistoricoEstacionamiento;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import external.ServicioDBpediaImpl;
import external.ServicioGeoNamesImpl;

public class ServicioEstaciones {

    private RepositorioBicicleta repositorioBicicleta;
    private RepositorioEstacion repositorioEstacion;
    private RepositorioHistoricoEstacionamiento repositorioHistorico;
    private int radio;

    // Otros repositorios si son necesarios

    public ServicioEstaciones(RepositorioBicicleta repositorioBicicleta, RepositorioEstacion repositorioEstacion, 
                              RepositorioHistoricoEstacionamiento repositorioHistorico /*, otros repositorios */) {
        this.repositorioBicicleta = repositorioBicicleta;
        this.repositorioEstacion = repositorioEstacion;
        this.repositorioHistorico = repositorioHistorico;
        // Inicializar otros repositorios
    }

    

    
    //Al crear/modificar las entidades estaría bien comprobar los campos para que no sean vacíos.
    
    public String altaBicicleta(String modelo, String estacionId) throws ServicioException {
        // Validar campos
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new ServicioException("El modelo no puede estar vacío.");
        }
        if (estacionId == null || estacionId.trim().isEmpty()) {
            throw new ServicioException("El ID de la estación no puede estar vacío.");
        }

        // Crear y guardar la nueva bicicleta
        Bicicleta nuevaBicicleta = new Bicicleta();
        nuevaBicicleta.setModelo(modelo);
        nuevaBicicleta.setFechaAlta(new Date());
        Bicicleta bicicletaGuardada = repositorioBicicleta.save(nuevaBicicleta);

        // Convertir String a ObjectId
        ObjectId objectId = new ObjectId(estacionId);

        // Buscar la estación y agregar el ID de la bicicleta a ella
        Estacion estacion = repositorioEstacion.findById(objectId);
        if (estacion != null) {
            estacion.getBicicletaIds().add(bicicletaGuardada.getId()); // Suponiendo que getId() devuelve el ID de la bicicleta
            repositorioEstacion.save(estacion);
        }

        return bicicletaGuardada.getCodigo();
    }
    
    public void estacionarBicicleta(String codigoBicicleta, String estacionId) throws ServicioException {
        // Validar campos
        if (codigoBicicleta == null || codigoBicicleta.trim().isEmpty()) {
            throw new ServicioException("El código de la bicicleta no puede estar vacío.");
        }
        if (estacionId == null || estacionId.trim().isEmpty()) {
            throw new ServicioException("El ID de la estación no puede estar vacío.");
        }

        ObjectId objectId = new ObjectId(estacionId);
        Estacion estacion = repositorioEstacion.findById(objectId);

        Bicicleta bicicleta = repositorioBicicleta.findById(codigoBicicleta);
        if (bicicleta == null) {
            throw new ServicioException("Bicicleta no encontrada.");
        }

        // Comprobar si la bicicleta ya está estacionada
        if (bicicleta.getEstacionId() != null) {
            throw new ServicioException("La bicicleta ya está estacionada en una estación.");
        }

        // Comprobar si hay espacio en la estación
        if (estacion.getBicicletaIds().size() >= estacion.getNumeroPuestos()) {
            throw new ServicioException("No hay espacio disponible en la estación.");
        }

        // Estacionar la bicicleta
        bicicleta.setEstacionId(estacion.getId()); // Aquí pasamos el ID de la estación, no el objeto Estacion
        repositorioBicicleta.save(bicicleta);

        // Agregar el ID de la bicicleta a la lista de IDs de bicicletas de la estación
        estacion.getBicicletaIds().add(bicicleta.getId());
        repositorioEstacion.save(estacion);

        // Crear y guardar el histórico de estacionamiento
        HistoricoEstacionamiento historico = new HistoricoEstacionamiento(bicicleta.getCodigo(), estacion.getId(), new Date());
        repositorioHistorico.save(historico);
    }


    public void retirarBicicleta(String codigoBicicleta) {
        Bicicleta bicicleta = repositorioBicicleta.findById(codigoBicicleta);
        if (bicicleta != null && bicicleta.getEstacionId() != null) {
            List<HistoricoEstacionamiento> historicos = repositorioHistorico.findAllByBicicletaCodigo(codigoBicicleta);
            HistoricoEstacionamiento ultimoHistorico = historicos.stream().filter(h -> h.getFechaFin() == null).findFirst().orElse(null);

            if (ultimoHistorico != null) {
                ultimoHistorico.setFechaFin(new Date());
                repositorioHistorico.save(ultimoHistorico);
            }

            bicicleta.setEstacionId(null);
            repositorioBicicleta.save(bicicleta);
        }
    }

    //Al dar de baja una bicicleta hay que retirarla también.
    //Correcion
    
    public void darDeBajaBicicleta(String codigoBicicleta, String motivo) {
        Bicicleta bicicleta = repositorioBicicleta.findById(codigoBicicleta);
        if (bicicleta != null) {
            // Retirar la bicicleta si está estacionada
            if (bicicleta.getEstacionId() != null) {
                retirarBicicleta(codigoBicicleta); // Este método ya maneja el retiro y la actualización del histórico
            }

            // Dar de baja la bicicleta
            bicicleta.setFechaBaja(new Date());
            bicicleta.setMotivoBaja(motivo);
            repositorioBicicleta.save(bicicleta);
        }
    }
    public List<Bicicleta> bicicletasCercanas(double latitud, double longitud) throws ServicioException {
        // Validar campos
        if (latitud < -90 || latitud > 90 || longitud < -180 || longitud > 180) {
            throw new ServicioException("Latitud o longitud fuera de rango.");
        }
        
        List<Estacion> estacionesCercanas = repositorioEstacion.findNearby(latitud, longitud, radio);

        return estacionesCercanas.stream()
                                 .flatMap(estacion -> estacion.getBicicletaIds().stream())
                                 .map(bicicletaId -> repositorioBicicleta.findById(bicicletaId))
                                 .filter(bicicleta -> bicicleta != null && bicicleta.getFechaBaja() == null)
                                 .collect(Collectors.toList());
    }


    
    //La función que devuelve las estaciones ordenadas por sitios turísticos se refiere  a los sitios turísticos ya asignados a la estación
    //(que por cierto no guardáis en mongodb), por lo que no hay que volver a llamar a geonames.

    public List<Estacion> estacionesConMasSitiosTuristicos() {
        // Obtener todas las estaciones
        List<Estacion> todasEstaciones = repositorioEstacion.findAll();

        // Ordenar las estaciones por el número de sitios turísticos asignados
        return todasEstaciones.stream()
                              .sorted((e1, e2) -> Integer.compare(
                                  e2.getSitiosTuristicosEstablecidos().size(),
                                  e1.getSitiosTuristicosEstablecidos().size())
                              )
                              .collect(Collectors.toList());
    }

}
