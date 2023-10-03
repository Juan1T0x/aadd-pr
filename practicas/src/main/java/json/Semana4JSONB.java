package json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;

public class Semana4JSONB {
    public static void main(String[] args) throws IOException {
        // Cargar el acta existente desde el archivo JSON
        JsonbConfig config = new JsonbConfig().withNullValues(true).withFormatting(true)
                .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
                .withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL);
        Jsonb jsonb = JsonbBuilder.create(config);
        Reader entrada = new FileReader("json/actaSemana4JSONP.json");
        Acta acta = jsonb.fromJson(entrada, Acta.class);
        entrada.close();

        // Incrementar en 0.5 puntos las notas de los alumnos
        for (Acta.Calificacion calificacion : acta.getCalificaciones()) {
            double nuevaNota = Double.parseDouble(calificacion.getNota()) + 0.5;
            calificacion.setNota(String.valueOf(Math.min(nuevaNota, 10.0)));
        }

        // Guardar el acta actualizada en un nuevo archivo JSON
        FileWriter salida = new FileWriter("json/actaSemana4JSONB.json");
        jsonb.toJson(acta, salida);
        salida.close();

        System.out.println("Acta actualizada y almacenada en actaSemana4JSONB.json");
    }
}
