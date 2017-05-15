package com.github.shenzhang.monitor.server.configuration.metrics;

import org.springframework.boot.autoconfigure.security.SecurityPrerequisite;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 3:19 PM.
 */
@ConfigurationProperties(prefix = "metrics.puller")
public class MetricsPullerProperties implements SecurityPrerequisite {
    private InfluxDbProperties influxdb = new InfluxDbProperties();

    public static class InfluxDbProperties {
        private String url;
        private String database;
        private String username;
        private String password;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public InfluxDbProperties getInfluxdb() {
        return influxdb;
    }

    public void setInfluxdb(InfluxDbProperties influxdb) {
        this.influxdb = influxdb;
    }
}
