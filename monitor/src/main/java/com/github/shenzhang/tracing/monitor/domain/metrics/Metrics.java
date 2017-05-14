package com.github.shenzhang.tracing.monitor.domain.metrics;

import com.github.shenzhang.tracing.monitor.domain.Application;

import java.util.Date;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 12:33 AM.
 */
public abstract class Metrics {
    private Date time;
    private String application;
    private String host;

    public abstract String getMeasurement();

    public void init(Application application) {
        this.application = application.getName();
        this.host = application.getHost();
        this.time = new Date();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
