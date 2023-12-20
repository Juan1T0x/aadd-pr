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

    public ServicioEstaciones(RepositorioBicicleta repositorioBicicleta, RepositorioEstacion repositorioEstacion, RepositorioHistoricoEstacionamiento repositorioHistorico) {
        this.repositorioBicicleta = repositorioBicicleta;
        this.repositorioEstacion = repositorioEstacion;
        this.repositorioHistorico = repositorioHistorico;
    }

    public String altaBicicleta(String modelo, String estacionId) {
        // Crear y guardar la nueva bicicleta
        Bicicleta nuevaBicicleta = new Bicicleta();
        nuevaBicicleta.setModelo(modelo);
        nuevaBicicleta.setFechaAlta(new Date());
        Bicicleta bicicletaGuardada = repositorioBicicleta.save(nuevaBicicleta);

        // Convertir String a ObjectId
        ObjectId objectId = new ObjectId(estacionId);

        // Buscar la estación y agregar la bicicleta a ella
        Estacion estacion = repositorioEstacion.findById(objectId);
        if (estacion != null) {
            estacion.getBicicletas().add(bicicletaGuardada);
            repositorioEstacion.save(estacion);
        }

        return bicicletaGuardada.getCodigo();
    }

    
    public void estacionarBicicleta(String codigoBicicleta, String estacionId) {
        Bicicleta bicicleta = repositorioBicicleta.findById(codigoBicicleta);

        // Convertir String a ObjectId
        ObjectId objectId = new ObjectId(estacionId);
        Estacion estacion = repositorioEstacion.findById(objectId);

        if (bicicleta != null && estacion != null) {
            bicicleta.setEstacion(estacion);
            repositorioBicicleta.save(bicicleta);

            HistoricoEstacionamiento historico = new HistoricoEstacionamiento(bicicleta.getCodigo(), estacion.getId(), new Date());
            repositorioHistorico.save(historico);
        }
    }
    

    public void retirarBicicleta(String codigoBicicleta) {
        Bicicleta bicicleta = repositorioBicicleta.findById(codigoBicicleta);
        if (bicicleta != null && bicicleta.getEstacionActual() != null) {
            List<HistoricoEstacionamiento> historicos = repositorioHistorico.findAllByBicicletaCodigo(codigoBicicleta);
            HistoricoEstacionamiento ultimoHistorico = historicos.stream().filter(h -> h.getFechaFin() == null).findFirst().orElse(null);

            if (ultimoHistorico != null) {
                ultimoHistorico.setFechaFin(new Date());
                repositorioHistorico.save(ultimoHistorico);
            }

            bicicleta.setEstacion(null);
            repositorioBicicleta.save(bicicleta);
        }
    }

    public void darDeBajaBicicleta(String codigoBicicleta, String motivo) {
        Bicicleta bicicleta = repositorioBicicleta.findById(codigoBicicleta);
        if (bicicleta != null) {
            bicicleta.setFechaBaja(new Date());
            bicicleta.setMotivoBaja(motivo);
            repositorioBicicleta.save(bicicleta);
        }
    }

    public List<Bicicleta> bicicletasCercanas(double latitud, double longitud) {
        
        List<Estacion> estacionesCercanas = repositorioEstacion.findNearby(latitud, longitud, radio);

        return estacionesCercanas.stream()
                                 .flatMap(estacion -> estacion.getBicicletas().stream())
                                 .filter(bicicleta -> bicicleta.getFechaBaja() == null) // Solo bicicletas disponibles
                                 .collect(Collectors.toList());
    }



    public List<Estacion> estacionesConMasSitiosTuristicos() {
        List<Estacion> todasEstaciones = repositorioEstacion.findAll();
        
        todasEstaciones.forEach(estacion -> {
            double latitud = estacion.getLatitud();
            double longitud = estacion.getLongitud();
            
            // Obtener sitios turísticos cercanos usando ServicioGeoNamesImpl
            List<SitioTuristico> sitiosCercanos = ServicioGeoNamesImpl.getInstance()
                                                                      .obtenerSitioTuristicoInteres(latitud, longitud);

            estacion.setSitiosTuristicosEstablecidos(sitiosCercanos);
        });

        return todasEstaciones.stream()
                              .sorted((e1, e2) -> e2.getSitiosTuristicosEstablecidos().size() - e1.getSitiosTuristicosEstablecidos().size())
                              .collect(Collectors.toList());
    }


}
