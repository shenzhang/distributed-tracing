package com.github.shenzhang.monitor.server.domain.metrics;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 12:33 AM.
 */
public class JvmMetrics extends Metrics {
    private int usedHeap;
    private int heap;

    @Override
    public String getMeasurement() {
        return "jvm";
    }

    public int getUsedHeap() {
        return usedHeap;
    }

    public void setUsedHeap(int usedHeap) {
        this.usedHeap = usedHeap;
    }

    public int getHeap() {
        return heap;
    }

    public void setHeap(int heap) {
        this.heap = heap;
    }
}
