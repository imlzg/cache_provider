package org.gaozou.kevin.cache.test;

import junit.framework.TestCase;
import org.gaozou.kevin.cache.Cache;
import org.gaozou.kevin.cache.CacheHolder;
import org.gaozou.kevin.cache.CacheManager;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class CacheTest extends TestCase {
    public void testCache() {
        String cacheName = "default";

        CacheManager cm = CacheHolder.getCacheManager();
        Cache cache = cm.getCache(cacheName);
        if (null == cache) {
            cm.addCache(cacheName);
            cache = cm.getCache(cacheName);
        }

        cache.put("test", "This is for test.");
        assertEquals("This is for test.", cache.get("test"));

        System.out.println(cache.get("test"));
    }
}
