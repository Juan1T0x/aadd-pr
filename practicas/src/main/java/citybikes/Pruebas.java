package citybikes;

import java.util.Date;

public class Pruebas {
    public static void main(String[] args) {
        
        // Registrar usuarios
    	Usuario usuario1 = new Usuario("usuario1@example.com", new java.sql.Date(new Date().getTime()), "123456789", "Usuario", "Uno");
    	Administrador admin = new Administrador("admin@example.com", new java.sql.Date(new Date().getTime()), "987654321", "Admin", "Uno");

    	// ...

    	Usuario operario = new Usuario("operario@example.com", new java.sql.Date(new Date().getTime()), "555444333", "Operario", "Uno");
        //Crear una bici
        Bici bici = new Bici("Bici 01");
        
        // Administrador crea una estacion
        Estacion estacion = admin.crearEstacion("Estacion Central");
        
        
        
        // Administrador da de alta una bici
        admin.darAltaBici(bici,estacion);
        
        // Usuario alquila una bici
        usuario1.alquilarBici(bici);
        
        // Simulamos que pasa tiempo
        try {
            Thread.sleep(1000);  // Espera 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Usuario devuelve la bici
        admin.darBajaBici(bici, "Estacion Central");
        
        // Usuario crea una incidencia
        Incidencia incidencia1 = new Incidencia("Bici con freno defectuoso", bici);
        
        // Administrador asigna la incidencia a un operario
       
        admin.asignarIncidenciaAOperario(incidencia1, operario);
        
        // Mostrar incidencias pendientes
        System.out.println("Incidencias pendientes: " + Incidencia.getIncidenciasPendientes());
    }
}