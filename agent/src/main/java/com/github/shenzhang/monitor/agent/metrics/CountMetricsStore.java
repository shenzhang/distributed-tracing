package com.github.shenzhang.monitor.agent.metrics;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: Zhang Shen
 * Date: 5/19/17
 * Time: 7:02 PM.
 */
@Component
public class CountMetricsStore {
    private Map<String, AtomicLong> counters = new ConcurrentHashMap<>();

    public void inc(String name) {
        getCounter(name).incrementAndGet();
    }

    public void inc(String name, long increasement) {
        getCounter(name).addAndGet(increasement);
    }

    public void dec(String name) {
        getCounter(name).decrementAndGet();
    }

    public long get(String name) {
        return getCounter(name).get();
    }

    public long set(String name, long value) {
        long current = get(name);
        getCounter(name).set(value);
        return current;
    }

    public long setAndGetDelta(String name, long value) {
        long current = set(name, value);
        return value - current;
    }

    public Set<String> getAllMetricsName() {
        return new HashSet<>(counters.keySet());
    }

    private AtomicLong getCounter(String name) {
        AtomicLong counter = counters.get(name);
        if (counter == null) {
            counter = new AtomicLong(0);
            AtomicLong existing = counters.putIfAbsent(name, counter);
            if (existing != null) {
                return existing;
            }
        }

        return counter;
    }
}
