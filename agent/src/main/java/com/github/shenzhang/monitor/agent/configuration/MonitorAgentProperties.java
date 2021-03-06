package com.github.shenzhang.monitor.agent.configuration;

import org.springframework.boot.autoconfigure.security.SecurityPrerequisite;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 3:19 PM.
 */
@ConfigurationProperties(prefix = "monitor.agent")
public class MonitorAgentProperties implements SecurityPrerequisite {
    private Tracing tracing = new Tracing();
    private Metrics metrics = new Metrics();

    public Tracing getTracing() {
        return tracing;
    }

    public void setTracing(Tracing tracing) {
        this.tracing = tracing;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public static class Tracing {
        public static final String TOGGLE = "monitor.agent.tracing.enabled";
        private boolean enabled;
        private String url;
        private String username;
        private String password;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public static class Metrics {
        public static final String TOGGLE = "monitor.agent.metrics.enabled";
        private boolean enabled;
        private String url;
        private String username;
        private String password;
        private int reportInterval = 5000;
        private int collectInterval = 1000;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getReportInterval() {
            return reportInterval;
        }

        public void setReportInterval(int reportInterval) {
            this.reportInterval = reportInterval;
        }

        public int getCollectInterval() {
            return collectInterval;
        }

        public void setCollectInterval(int collectInterval) {
            this.collectInterval = collectInterval;
        }
    }
}
