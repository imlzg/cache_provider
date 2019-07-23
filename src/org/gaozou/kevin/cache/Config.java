package org.gaozou.kevin.cache;

import org.gaozou.kevin.utility.StringUtil;

import java.util.Properties;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class Config {
    private static final String CACHE_NAME_KEY        = "cache_name";
    private static final String LIVE_SECONDS_KEY      = "live_seconds";

    private static final String CACHE_NAME_DEFAULT    = "default";
    private static final int    LIVE_SECONDS_DEFAULT  = 3600;


    public final Properties props;
    public Config(Properties props) {
        this.props = props;
    }

    public String getCacheName() {
        return props.getProperty(CACHE_NAME_KEY, CACHE_NAME_DEFAULT);
    }
    public Integer getLiveSeconds() {
        String v =  props.getProperty(LIVE_SECONDS_KEY);
        return StringUtil.isEmpty(v) ? LIVE_SECONDS_DEFAULT : Integer.valueOf(v);
    }
}
