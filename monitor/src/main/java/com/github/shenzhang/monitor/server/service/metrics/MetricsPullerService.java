package com.github.shenzhang.monitor.server.service.metrics;

import com.github.shenzhang.monitor.server.dao.MetricsDao;
import com.github.shenzhang.monitor.server.domain.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 12:53 AM.
 */
@Service
public class MetricsPullerService implements SmartLifecycle {
    private ScheduledExecutorService executorService;
    @Autowired
    private MetricsPullerBuilder pullerBuilder;
    private List<Application> applications = newArrayList(
            new Application("example-api", "localhost", "http://localhost:10000/")
    );

    @Autowired
    private MetricsDao metricsDao;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        this.stop();
        callback.run();
    }

    @Override
    public void start() {
        executorService = Executors.newScheduledThreadPool(20);

        for (Application application : applications) {
            executorService.scheduleAtFixedRate(pullerBuilder.build(application), 0L, 5L, TimeUnit.SECONDS);
        }
    }

    @Override
    public void stop() {
        if (!executorService.isShutdown()) {
            executorService.shutdownNow();
            executorService = null;
        }
    }

    @Override
    public boolean isRunning() {
        return executorService != null && !executorService.isShutdown();
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
