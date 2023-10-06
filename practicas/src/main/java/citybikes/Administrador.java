package citybikes;

import java.sql.Date;

public class Administrador extends Usuario {
    
	//Constructor de Administrador.
    public Administrador(String email, Date fechaDeNacimiento, String telefono, String nombre, String apellidos) {
		super(email, fechaDeNacimiento, telefono, nombre, apellidos);
		// TODO Auto-generated constructor stub
	}

    //Crear una nueva Estacion 
    public Estacion crearEstacion(String nombre) {
        Estacion nuevaEstacion = new Estacion(nombre);
        Estacion.getTodasLasEstaciones().add(nuevaEstacion); // Agrega la nueva estación a la lista
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

    public void darBajaBici(Bici bici, String motivo) {
        // Función para dar de baja a una bici
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
    public void asignarIncidenciaAOperario(Incidencia incidencia, Usuario operario) {
        if (Incidencia.getIncidenciasPendientes().contains(incidencia)) {
            incidencia.setOperarioAsignado(operario);
            incidencia.setEstado(Estado.ASIGNADO); // Asumiendo que tienes un estado "ASIGNADA" en tu Enum
            Incidencia.getIncidenciasPendientes().remove(incidencia); // Remueve la incidencia de la lista de pendientes
            System.out.println("Incidencia asignada a " + operario.getNombre());
        } else {
            System.out.println("Incidencia no encontrada o ya asignada.");
        }
    }
    
    
    
    
}