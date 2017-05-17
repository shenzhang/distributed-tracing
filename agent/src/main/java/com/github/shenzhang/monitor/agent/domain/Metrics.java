package com.github.shenzhang.monitor.agent.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Zhang Shen
 * Date: 5/17/17
 * Time: 2:51 PM.
 */
public class Metrics {
    private long time;
    private String measurement;
    private String application;
    private String host;
    private Map<String, Double> fields = new HashMap<>();

    public Metrics addField(String name, double value) {
        fields.put(name, value);
        return this;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
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

    public Map<String, Double> getFields() {
        return fields;
    }

    public void setFields(Map<String, Double> fields) {
        this.fields = fields;
    }
}
