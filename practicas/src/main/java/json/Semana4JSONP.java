package json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class Semana4JSONP {
	public static void main(String[] args) throws FileNotFoundException {

		// Ruta al archivo JSON
		String filePath = "json/Semana4JSONP.json";

		// Crear un InputStream desde el archivo JSON
		InputStream inputStream = new FileInputStream(filePath);

		// Crear un InputStreamReader para el InputStream
		InputStreamReader reader = new InputStreamReader(inputStream);

		// Crear un JsonReader desde el InputStreamReader
		JsonReader jsonReader = Json.createReader(reader);

		// Leer el objeto JSON
		JsonObject json = jsonReader.readObject();

		// Obtener el array de calificaciones
		JsonArray calificaciones = json.getJsonObject("acta").getJsonArray("calificacion");

		int totalNotas = 0;

		// Sumar todas las notas
		for (int i = 0; i < calificaciones.size(); i++) {
			JsonObject calificacion = calificaciones.getJsonObject(i);
			int nota = Integer.parseInt(calificacion.getString("nota"));
			totalNotas += nota;
		}

		// Calcular la nota media
		double notaMedia = (double) totalNotas / calificaciones.size();

		// Imprimir la nota media
		System.out.println("Nota media: " + notaMedia);

		// Obtener el array de diligencias
		JsonArray diligencias = json.getJsonObject("acta").getJsonArray("diligencia");

		// Obtener la fecha actual
		LocalDate fechaActual = LocalDate.now();

		// Contador para diligencias en el último mes
		int diligenciasUltimoMes = 0;

		// Formateador para las fechas en el JSON
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

		// Iterar sobre las diligencias y contar las que están en el último mes
		for (int i = 0; i < diligencias.size(); i++) {
			JsonObject diligencia = diligencias.getJsonObject(i);
			String fechaDiligenciaStr = diligencia.getString("@fecha");
			LocalDate fechaDiligencia = LocalDate.parse(fechaDiligenciaStr, formatter);

			// Verificar si la diligencia está en el último mes
			if (fechaDiligencia.isAfter(fechaActual.minusMonths(1))) {
				diligenciasUltimoMes++;
			}
		}

		// Imprimir la cantidad de diligencias en el último mes
		System.out.println("Cantidad de diligencias en el último mes: " + diligenciasUltimoMes);

		// Construir el objeto JSON para el acta
		JsonObjectBuilder actaBuilder = Json.createObjectBuilder().add("@convocatoria", "febrero").add("@curso", "2023")
				.add("@asignatura", "1092")
				.add("calificacion",
						Json.createArrayBuilder()
								.add(Json.createObjectBuilder().add("nif", "23322156M").add("nota", "10"))
								.add(Json.createObjectBuilder().add("nif", "13322156M").add("nombre", "Pepe")
										.add("nota", "8")))
				.add("diligencia", Json.createObjectBuilder().add("@fecha", "2023-09-12").add("nif", "13322156M")
						.add("nota", "9"));

		// Construir el objeto JSON completo
		JsonObject actaJson = Json.createObjectBuilder().add("acta", actaBuilder).build();
		
        try (JsonWriter jsonWriter = Json.createWriter(new FileWriter("json/actaSemana4JSONP.json"))) {
            jsonWriter.writeObject(actaJson);
            System.out.println("Acta JSON creada y almacenada en acta.json");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }

	}
}
