package com.github.shenzhang.monitor.agent.configuration;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * User: Zhang Shen
 * Date: 5/25/17
 * Time: 11:07 AM.
 */
@Configuration
public class HttpConnectionConfiguration {
    private HttpClientConnectionManager connectionManager;
    private CloseableHttpClient httpClient;

    @Bean("monitorAgentHttpClient")
    public HttpClient httpClient() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setDefaultMaxPerRoute(5);
        connManager.setMaxTotal(20);

        this.httpClient = HttpClients.custom().setConnectionManager(connManager).build();
        this.connectionManager = connManager;

        return this.httpClient;
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void closeExpiredConnection() {
        if (connectionManager != null) {
            connectionManager.closeExpiredConnections();
        }
    }

    @PreDestroy
    public void close() {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
    }
}
