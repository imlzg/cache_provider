package org.gaozou.kevin.cache;

import org.gaozou.kevin.utility.DigestUtil;
import org.gaozou.kevin.utility.StringUtil;

import java.util.regex.Pattern;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class Md5KeyStrategy implements KeyStrategy {
    private final Pattern CLEAN_PATTERN = Pattern.compile("\\s");

    public String toKey(String key) {
        if (StringUtil.isEmpty(key)) throw new IllegalArgumentException("key must not be null");

        key = CLEAN_PATTERN.matcher(key).replaceAll("");
        return DigestUtil.md5Hex(key);
    }
}
