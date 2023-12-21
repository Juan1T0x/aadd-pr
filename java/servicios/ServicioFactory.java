package servicios;

import repositorios.RepositorioBicicleta;
import repositorios.RepositorioEstacion;
import repositorios.RepositorioFactory;



import repositorios.*;
public class ServicioFactory {


	
    public static ServicioEstaciones crearServicioEstaciones() {
    	RepositorioBicicleta repositorioBicicleta = RepositorioFactory.crearRepositorioBicicleta();
        RepositorioEstacion repositorioEstacion = RepositorioFactory.crearRepositorioEstacion();
        RepositorioHistoricoEstacionamiento repositorioHistorico = RepositorioFactory.crearRepositorioHistoricoEstacionamiento();
        // Instanciar otros repositorios si son necesarios

        return new ServicioEstaciones(repositorioBicicleta, repositorioEstacion, repositorioHistorico /*, otros repositorios */);
    }

    // Métodos para crear otros servicios...
}