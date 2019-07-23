package org.gaozou.kevin.cache;

import org.gaozou.kevin.utility.PropertiesUtil;
import org.gaozou.kevin.utility.ResourceUtil;
import org.gaozou.kevin.utility.SimpleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public final class CacheHolder {
    private static final Logger log = LoggerFactory.getLogger(CacheHolder.class);
    private static final String configFile = "cache.properties";

    private static Cache cache = null;
    
    private static Cache createCache() {
        Properties config = PropertiesUtil.load(configFile);
        String m = config.getProperty("cache_manager", "org.gaozou.kevin.cache.MemcachedCacheManager");
        try {
            CacheManager manager = (CacheManager) ResourceUtil.classForName(m).newInstance();
            return manager.createCache(config);
        } catch (Exception e) {
            log.warn("Could not instance {}", m);
            throw new SimpleException("Could not instance " + m, e);
        }
    }
    public synchronized static Cache getCache() {
        if (null == cache) cache = createCache();
        return cache;
    }

    public static void shutdown() {
        cache.shutdown();
    }
}
