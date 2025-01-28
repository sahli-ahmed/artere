package artere.test.cache;

import lombok.*;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
@ToString
public class Cache {
    private final Map<String, String> cache = new HashMap<>();

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }

}
