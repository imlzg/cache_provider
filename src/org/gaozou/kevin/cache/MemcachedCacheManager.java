package org.gaozou.kevin.cache;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.DefaultConnectionFactory;
import net.spy.memcached.HashAlgorithm;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class MemcachedCacheManager implements CacheManager {
    private static final Logger log = LoggerFactory.getLogger(MemcachedCacheManager.class);

    public Cache createCache(Properties props) throws Exception {
        MemcachedConfig c = new MemcachedConfig(props);
        return new MemcachedCache(c.getCacheName(), c.getLiveSeconds(), getClient(c));
    }

    public MemcachedClient getClient(final MemcachedConfig config) throws Exception {
        DefaultConnectionFactory dcf =
        new DefaultConnectionFactory(config.getOperationQueueLength(), config.getReadBufferSize(), HashAlgorithm.KETAMA_HASH) {
            public long getOperationTimeout() {
                return config.getOperationTimeoutMillis();
            }
        };
        return new MemcachedClient(dcf, AddrUtil.getAddresses(config.getServerList()));
    }

    public void destroy(Cache cache) {
        log.debug("Shutting down spy MemcachedClient: " + cache.getName());
        cache.shutdown();
    }
}
