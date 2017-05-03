package com.github.shenzhang.tracing.agent.forwarder;

import com.github.shenzhang.tracing.agent.configuration.TracingAgentProperties;
import com.github.shenzhang.tracing.agent.domain.Span;
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

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 12:36 AM.
 */
public class SpanForwarder {
    private static Logger LOGGER = LoggerFactory.getLogger(SpanForwarder.class);

    @Autowired
    private TracingAgentProperties properties;

    public void forward(Span span) {
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

            int responseCode = connection.getResponseCode();
            LOGGER.info("Response code = {}", responseCode);

            connection.disconnect();
        } catch (Exception e) {
            LOGGER.error("Send span failed", e);
        }
    }
}
