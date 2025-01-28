package artere.test.cache.controller;

import artere.test.cache.Cache;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final Cache cache = new Cache();

    @PostMapping
    public String put(@RequestParam String key, @RequestParam String value) {
        cache.put(key, value);
        return "Cached successfully";
    }

    @GetMapping
    public String get(@RequestParam String key) {
        String value = cache.get(key);
        return value != null ? value : "Key not found in cache";
    }

    @DeleteMapping("/key")
    public String remove(@RequestParam String key) {
        if (cache.get(key) != null) {
            cache.remove(key);
            return "Key removed from cache";
        }
        return "Key not found in cache";
    }

    @DeleteMapping
    public String clear() {
        cache.clear();
        return "Cache cleared";
    }

    @GetMapping("/size")
    public int size() {
        return cache.size();
    }
}
