package com.github.shenzhang.monitor.agent.util;

import org.apache.http.HttpEntity;

import java.io.InputStream;

/**
 * User: Zhang Shen
 * Date: 5/25/17
 * Time: 5:12 PM.
 */
public class HttpClientUtils {
    public static void consumeEntity(HttpEntity entity) {
        try {
            if (entity != null && entity.isStreaming()) {
                InputStream inputStream = entity.getContent();
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (Exception ignore) {
        }
    }
}
