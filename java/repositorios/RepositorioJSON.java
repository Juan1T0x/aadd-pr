package repositorios;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;

public abstract class RepositorioJSON {

    private static final String DIRECTORY = "path/to/your/json/files/"; // Ajusta el directorio según sea necesario

    public void save(Object obj, String fileName) {
        Jsonb jsonb = null;
        try {
            jsonb = JsonbBuilder.create();
            String filePath = DIRECTORY + fileName;
            try (FileWriter writer = new FileWriter(filePath)) {
                jsonb.toJson(obj, writer);
            }
        } catch (IOException | JsonbException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // Manejo de otras excepciones generales
            e.printStackTrace();
        } finally {
            if (jsonb != null) {
                try {
                    jsonb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public <T> T load(String fileName, Class<T> type) {
        Jsonb jsonb = null;
        try {
            jsonb = JsonbBuilder.create();
            String filePath = DIRECTORY + fileName;
            try (FileReader reader = new FileReader(filePath)) {
                return jsonb.fromJson(reader, type);
            }
        } catch (IOException | JsonbException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // Manejo de otras excepciones generales
            e.printStackTrace();
        } finally {
            if (jsonb != null) {
                try {
                    jsonb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // Elimina un archivo JSON
    public void delete(String fileName) {
        String filePath = DIRECTORY + fileName;
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
