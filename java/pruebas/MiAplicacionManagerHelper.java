package pruebas;

import external.EntityManagerHelper;
import repositorios.*;

public class MiAplicacionManagerHelper {
    public static void main(String[] args) {
        try {
            RepositorioBicicleta repositorioBicicleta = new RepositorioBicicletaJPA();
            // Operaciones con el repositorio

        } finally {
            EntityManagerHelper.closeEntityManager(); // Cerrar al final del uso
        }
        EntityManagerHelper.closeEntityManagerFactory(); // Cerrar cuando la aplicación termine
    }
}