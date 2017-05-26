package com.github.shenzhang.monitor.agent.tracing;

import com.github.shenzhang.monitor.agent.domain.Span;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/17/17
 * Time: 3:16 PM.
 */
public class TracingRepository {
    private static final int CAPACITY = 1000;
    private List<Span> buffer = newBuffer();

    public synchronized void add(Span span) {
        if (buffer.size() < CAPACITY) {
            buffer.add(span);
        }
    }

    public synchronized List<Span> get() {
        List<Span> list = buffer;
        buffer = newBuffer();
        return list;
    }

    public synchronized int count() {
        return buffer.size();
    }

    private List<Span> newBuffer() {
        return new ArrayList<>(100);
    }
}
