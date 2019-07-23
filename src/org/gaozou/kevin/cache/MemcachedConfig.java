package org.gaozou.kevin.cache;

import net.spy.memcached.DefaultConnectionFactory;
import org.gaozou.kevin.utility.StringUtil;

import java.util.Properties;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public final class MemcachedConfig extends Config {
    private static final String SERVER_LIST_KEY                = "memcached_servers";
    private static final String OPERATION_QUEUE_LENGTH_KEY     = "memcached_operation_queue";
    private static final String OPERATION_TIMEOUT_MILLIS_KEY   = "memcached_operation_timeout";
    private static final String READ_BUFFER_SIZE_KEY           = "memcached_read_buffer";


    public MemcachedConfig(Properties props) {
        super(props);
    }


    public String getServerList() {
        return props.getProperty(SERVER_LIST_KEY, "localhost:11211");
    }

    public int getOperationQueueLength() {
        String v =  props.getProperty(OPERATION_QUEUE_LENGTH_KEY);
        return StringUtil.isEmpty(v) ? DefaultConnectionFactory.DEFAULT_OP_QUEUE_LEN : Integer.valueOf(v);
    }

    public long getOperationTimeoutMillis() {
        String v = props.getProperty(OPERATION_TIMEOUT_MILLIS_KEY);
        return StringUtil.isEmpty(v) ? DefaultConnectionFactory.DEFAULT_OPERATION_TIMEOUT : Integer.valueOf(v);
    }

    public int getReadBufferSize() {
        String v = props.getProperty(READ_BUFFER_SIZE_KEY);
        return StringUtil.isEmpty(v) ? DefaultConnectionFactory.DEFAULT_READ_BUFFER_SIZE : Integer.valueOf(v);
    }
}
