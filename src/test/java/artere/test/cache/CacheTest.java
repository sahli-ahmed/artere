package artere.test.cache;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CacheTest {

	@Test
	void testCacheOperations() {
		Cache cache = new Cache();

		// Test put and get
		cache.put("key1", "value1");
		assertEquals("value1", cache.get("key1"));

		// Test remove
		cache.remove("key1");
		assertNull(cache.get("key1"));

		// Test clear
		cache.put("key2", "value2");
		cache.clear();
		assertEquals(0, cache.size());
	}

}
