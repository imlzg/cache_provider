package org.gaozou.kevin.cache;

import java.util.Properties;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public interface CacheManager {
    Cache createCache(Properties props) throws Exception;

    void destroy(Cache cache);
}
