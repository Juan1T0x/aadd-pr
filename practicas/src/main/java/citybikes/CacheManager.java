package citybikes;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CacheManager {
    private static final Path CACHE_FILE_PATH = Paths.get("sitiosTuristicosCache.json");
    private static final Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
    
    private Map<String, SitioTuristico> cache;

    public CacheManager() {
        cache = new HashMap<>();
        loadCacheFromFile();
    }

    public void saveToCache(String key, SitioTuristico sitio) {
        cache.put(key, sitio);
        saveCacheToFile();
    }

    public SitioTuristico getFromCache(String key) {
        return cache.get(key);
    }

    private void loadCacheFromFile() {
        if (Files.exists(CACHE_FILE_PATH)) {
            try (Reader reader = Files.newBufferedReader(CACHE_FILE_PATH)) {
                cache = jsonb.fromJson(reader, new HashMap<String, SitioTuristico>().getClass());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveCacheToFile() {
        try (Writer writer = Files.newBufferedWriter(CACHE_FILE_PATH)) {
            jsonb.toJson(cache, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

