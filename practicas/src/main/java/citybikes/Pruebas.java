package citybikes;

import java.util.Date;
import java.util.List;

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
        Estacion estacion = admin.crearEstacion("Estacion Central", 100, "Calle princesa",  "Latitud: 40.712784 | Longitud: -74.005941");
        
        
        
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
        
        System.out.println("Pruebas de la segunda parte");
        System.out.println("");
        
        
        
        Administrador admin2 = new Administrador("admin2@example.com", new java.sql.Date(new Date().getTime()), "7656523421", "Admin2", "Dos");
        
        // 1. Crear una estación y comprobar que se ha añadido correctamente
        Estacion estacionCreada = admin2.crearEstacion("Estacion Central", 20, "123 Calle Principal", "40.7128,-74.0060");
        System.out.println("Estación creada con ID: " + estacionCreada.getId());

        // 2. Obtener sitios turísticos próximos (función placeholder)
        List<String> sitios = admin2.obtenerSitiosTuristicosProximos(estacionCreada.getId());
        System.out.println("Sitios turísticos próximos (placeholder): " + sitios);

        // 3. Establecer sitios turísticos para una estación y comprobar que se han establecido correctamente
        admin2.establecerSitiosTuristicos(estacionCreada.getId(), sitios);
        Estacion estacionActualizada = admin2.obtenerEstacion(estacionCreada.getId());
        System.out.println("Sitios turísticos establecidos para la estación: " + estacionActualizada.getSitiosTuristicos());
        
        // 4. Obtener una estación por ID y comprobar que se recupera correctamente
        Estacion estacionObtenida = admin2.obtenerEstacion(estacionCreada.getId());
        if (estacionObtenida != null) {
            System.out.println("Estación obtenida con ID: " + estacionObtenida.getId());
        } else {
            System.out.println("No se encontró la estación con el ID especificado.");
        }
    }
            
        
    }
