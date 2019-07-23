package org.gaozou.kevin.cache;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public interface KeyStrategy {
    String toKey(String key);
}
