package com.github.shenzhang.monitor.server.domain;

import com.google.common.base.Objects;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 1:14 AM.
 */
public class Application {
    private String name;
    private String host;
    private String url;

    public Application(String name, String host, String url) {
        this.name = name;
        this.host = host;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else if (obj.getClass() != getClass()) {
            return false;
        }

        Application other = (Application)obj;
        return Objects.equal(name, other.name) && Objects.equal(host, other.host);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, host);
    }
}
