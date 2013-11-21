package example8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CacheTest {
    @Test
    public void simple() {
        Cache cache = new Cache();

        cache.set("Hello", "World");
        assertEquals("World", cache.get("Hello").get());
        assertTrue(cache.delete("Hello"));
        assertFalse(cache.delete("Hello"));
    }
}
