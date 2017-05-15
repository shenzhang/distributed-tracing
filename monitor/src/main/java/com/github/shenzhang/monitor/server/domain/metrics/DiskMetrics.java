package com.github.shenzhang.monitor.server.domain.metrics;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 12:33 AM.
 */
public class DiskMetrics extends Metrics {
    private long used;
    private long free;

    @Override
    public String getMeasurement() {
        return "disk";
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getFree() {
        return free;
    }

    public void setFree(long free) {
        this.free = free;
    }
}
