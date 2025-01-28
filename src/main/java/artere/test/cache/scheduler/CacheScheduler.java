package artere.test.cache.scheduler;

import artere.test.cache.Cache;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
@AllArgsConstructor
public class CacheScheduler {

    private final Cache cache;

    @Value("${cache.ttl:3600000}") // TTL configuré dans application.properties, par défaut 1 heure
    private long ttl;

    @Scheduled(cron = "0 0 * * * ?") // Exécute la tâche chaque heure
    public void cleanCache() {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<String, String>> iterator = cache.getCache().entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            // Si une clé est plus vieille que le TTL, on la supprime
            if (isExpired(entry.getKey(), currentTime)) {
                iterator.remove();
            }
        }
    }

    private boolean isExpired(String key, long currentTime) {
        // TODO
        return false;
    }
}
