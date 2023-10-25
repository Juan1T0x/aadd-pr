package external;

import java.util.List;

import modelo.SitioTuristico;
import modelo.SitioTuristicoCompleto;

public interface ServicioDBpedia {
	SitioTuristicoCompleto obtenerInformacionSitioTuristico(String nombre);
}
