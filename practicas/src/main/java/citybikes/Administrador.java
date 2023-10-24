package citybikes;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class Administrador extends Usuario {
	
	
	private List<Incidencia> incidenciasAsignadas;
    
	//Constructor de Administrador.
    public Administrador(String email, Date fechaDeNacimiento, String telefono, String nombre, String apellidos) {
		super(email, fechaDeNacimiento, telefono, nombre, apellidos);
		// TODO Auto-generated constructor stub
	}

    //Alta de una estación de aparcamiento con la información
    
    public Estacion crearEstacion(String nombre, int numeroDePuestos, String direccion, String coordenadasGeograficas) {
        Estacion nuevaEstacion = new Estacion(nombre, numeroDePuestos, direccion, coordenadasGeograficas);
        Estacion.addEstacionToTodas(nuevaEstacion);
        return nuevaEstacion;
    }
    
    
	// Función para dar de alta una bici
    public void darAltaBici(Bici bici, Estacion estacion) {
        bici.setFechaBaja(null);
        bici.setMotivoBaja("");
        bici.setAlquilada(false); // Asegurarse de que la bicicleta no esté alquilada cuando se da de alta
        estacion.agregarBici(bici);
        System.out.println("Bicicleta con código " + bici.getCodigo() + " dada de alta en estación " + estacion.getNombre());
    }

 // Función para dar de baja a una bici
    public void darBajaBici(Bici bici, String motivo) {  
        Estacion estacion = bici.getEstacion();
        if(estacion != null) {
            estacion.removerBici(bici);
            bici.setFechaBaja(new Date(0));
            bici.setMotivoBaja(motivo);
            System.out.println("Bicicleta con código " + bici.getCodigo() + " dada de baja en estación " + estacion.getNombre());
        } else {
            System.out.println("Bicicleta con código " + bici.getCodigo() + " no se encuentra en ninguna estación.");
        }
    }
    
    
    
 // Método para asignar una incidencia a un operario
    public void asignarIncidenciaAOperario(Incidencia incidencia, Administrador operario) {

        if (Incidencia.getIncidenciasPendientes().contains(incidencia)) {
            incidencia.setOperarioAsignado(operario);
            incidencia.setEstado(Estado.ASIGNADO); // Asumiendo que tienes un estado "ASIGNADA" en tu Enum
            Incidencia.getIncidenciasPendientes().remove(incidencia); // Remueve la incidencia de la lista de pendientes
            System.out.println("Incidencia asignada a " + operario.getNombre());
        } else {
            System.out.println("Incidencia no encontrada o ya asignada.");
        }
    }
    
    
         // Importante esta función.
    public List<String> obtenerSitiosTuristicosProximos(String estacionId) {
        // Esta función es un la base. 
        // sitios turísticos próximos usando, API .
        return Arrays.asList("Sitio Turístico 1", "Sitio Turístico 2", "Sitio Turístico 3");
    }
    
    
    //Establecer sitios turísticos. Esta operación recibe como parámetro el identificador y una colección de
    //sitios turísticos que van a quedar asociados a la estación.

    public void establecerSitiosTuristicos(String estacionId, List<String> sitios) {
        for (Estacion estacion : Estacion.getTodasLasEstaciones()) {
            if (estacion.getId().equals(estacionId)) {
                estacion.setSitiosTuristicos(sitios);
            }
        }
    }
    
    

 // Obtener una estación 
    public Estacion obtenerEstacion(String estacionId) {
        for (Estacion estacion : Estacion.getTodasLasEstaciones()) {
            if (estacion.getId().equals(estacionId)) {
                return estacion;
            }
        }
        return null;  // si no se encuentra la estación con el ID dado
    }
    
    
    
    
}

