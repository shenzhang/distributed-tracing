package com.github.shenzhang.tracing.monitor.configuration;

import org.springframework.boot.autoconfigure.security.SecurityPrerequisite;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 3:19 PM.
 */
@ConfigurationProperties(prefix = "tracing.monitor")
public class TracingMonitorProperties implements SecurityPrerequisite {
    private Cassandra cassandra = new Cassandra();

    public Cassandra getCassandra() {
        return cassandra;
    }

    public void setCassandra(Cassandra cassandra) {
        this.cassandra = cassandra;
    }

    public static class Cassandra {
        private String[] servers;
        private String keyspace;
        private String username;
        private String password;

        public String[] getServers() {
            return servers;
        }

        public void setServers(String[] servers) {
            this.servers = servers;
        }

        public String getKeyspace() {
            return keyspace;
        }

        public void setKeyspace(String keyspace) {
            this.keyspace = keyspace;
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
