package com.github.shenzhang.monitor.server.domain;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 12:33 AM.
 */
public class Metrics {
    private long time;
    private String measurement;
    private String application;
    private String host;
    private Map<String, Double> fields = newHashMap();

    public Metrics() {
    }

    public Metrics(Application application) {
        this.application = application.getName();
        this.host = application.getHost();
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Map<String, Double> getFields() {
        return fields;
    }

    public void setFields(Map<String, Double> fields) {
        this.fields = fields;
    }

    public void addFieldValue(String name, double value) {
        this.fields.put(name, value);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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
