package com.github.shenzhang.tracing.agent.configuration;

import org.springframework.boot.autoconfigure.security.SecurityPrerequisite;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 3:19 PM.
 */
@ConfigurationProperties(prefix = "tracing.agent")
public class TracingAgentProperties implements SecurityPrerequisite {
    private Monitor monitor = new Monitor();

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public static class Monitor {
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
    }
}
