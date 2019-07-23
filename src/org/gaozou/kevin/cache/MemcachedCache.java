package org.gaozou.kevin.cache;

import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class MemcachedCache implements Cache {
    private static final Logger log = LoggerFactory.getLogger(MemcachedCache.class);

    private final String name;
    private final int liveSeconds;
    private final MemcachedClient client;
    private final KeyStrategy keyStrategy = new Md5KeyStrategy();

    public MemcachedCache(String name, int liveSeconds, MemcachedClient client) {
        this.name        = name;
        this.liveSeconds = liveSeconds;
        this.client      = client;
    }

    public String getName() {
        return name;
    }

    public Object get(String key) {
        log.debug("get: {}", key);
        try {
            return client.get(genKey(key));
        } catch (Exception e) {
            log.warn("problem get: {}", key);
        }
        return null;
    }

    public void put(String key, Object value) {
        put(key, liveSeconds, value);
    }

    public void put(String key, int liveSeconds, Object value) {
        log.debug("put: {}", key);
        try {
            client.set(genKey(key), liveSeconds, value);
        } catch (Exception e) {
            log.warn("problem put: {}", key);
        }
    }

    public void remove(String key) {
        log.debug("remove: {}", key);
        try {
            client.delete(genKey(key));
        } catch (Exception e) {
            log.warn("problem remove: {}", key);
        }
    }

    public void shutdown() {
        if (client.isAlive()) {
            log.debug("Shutting down Memcache client");
            client.shutdown();
        }
    }

    public String toString() {
        return "This is the spy MemCachedClient cache: " + name;
    }

    private String genKey(String key) {
        return keyStrategy.toKey(getName() + ":" + key);
    }
}
