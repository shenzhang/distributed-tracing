package com.github.shenzhang.monitor.agent.reporter;

import com.github.shenzhang.monitor.agent.configuration.MonitorAgentProperties;
import com.github.shenzhang.monitor.agent.domain.Span;
import com.github.shenzhang.monitor.agent.tracing.TracingRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 12:36 AM.
 */
@Component
public class DistributedTracingReporter {
    private static Logger LOGGER = LoggerFactory.getLogger(DistributedTracingReporter.class);

    @Autowired
    private TracingRepository repository;

    @Autowired
    private MonitorAgentProperties properties;

    @Scheduled(fixedRate = 5000)
    public void report() {
        List<Span> spans = repository.get();
        if (spans.isEmpty()) {
            return;
        }

        try {
            String urlString = properties.getTracing().getUrl();

            URL url = new URI(urlString).toURL();
            URLConnection urlConnection = url.openConnection();
            if (!(urlConnection instanceof HttpURLConnection)) {
                throw new Exception(String.format("Initialize connection failed, url = %s", urlString));
            }

            HttpURLConnection connection = (HttpURLConnection)urlConnection;
            connection.setInstanceFollowRedirects(false);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Content-Type", "application/json");

            connection.connect();
            OutputStream outputStream = connection.getOutputStream();

            Gson gson = new GsonBuilder().create();

            outputStream.write(gson.toJson(spans).getBytes());
            outputStream.flush();
            outputStream.close();

            connection.getResponseCode();
            connection.disconnect();

            LOGGER.info("Distributed tracing was reported");
        } catch (Exception e) {
            LOGGER.error("Send distributed-tracing span failed - {}:{}", e.getClass().getName(), e.getMessage());
        }
    }
}
