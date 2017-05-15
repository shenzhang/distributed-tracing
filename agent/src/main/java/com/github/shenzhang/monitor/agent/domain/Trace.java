package com.github.shenzhang.monitor.agent.domain;

import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/2/17
 * Time: 4:11 PM.
 */
public class Trace {
    private String id;
    private List<Span> spans;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Span> getSpans() {
        return spans;
    }

    public void setSpans(List<Span> spans) {
        this.spans = spans;
    }
}
