package com.github.shenzhang.monitor.agent.reporter;

import com.github.shenzhang.monitor.agent.configuration.TracingAgentProperties;
import com.github.shenzhang.monitor.agent.domain.Span;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 12:36 AM.
 */
public class DistributedTracingReporter implements Runnable {
    private static Logger LOGGER = LoggerFactory.getLogger(DistributedTracingReporter.class);

    private List<Span> backlog = new ArrayList<>();
    private List<Span> pending;

    @Autowired
    private TracingAgentProperties properties;

    public void report(Span span) {
        try {
            String urlString = properties.getMonitor().getUrl();

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

            Span[] spans = new Span[]{span};
            outputStream.write(gson.toJson(spans).getBytes());
            outputStream.flush();
            outputStream.close();

            connection.getResponseCode();
            connection.disconnect();
        } catch (Exception e) {
            LOGGER.error("Send distributed-tracing span failed - {}:{}", e.getClass().getName(), e.getMessage());
        }
    }

    @Override
    public void run() {

    }
}
