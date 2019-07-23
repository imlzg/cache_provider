package org.gaozou.kevin.cache;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public interface Cache {
    String getName();

    Object get(String key);

    void put(String key, Object o);

    void put(String key, int liveSeconds, Object o);

    void remove(String key);

    void shutdown();
}
