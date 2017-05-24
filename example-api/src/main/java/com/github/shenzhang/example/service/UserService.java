package com.github.shenzhang.example.service;

import com.github.shenzhang.monitor.agent.annotation.CountMetrics;
import com.github.shenzhang.example.domain.User;
import com.github.shenzhang.monitor.agent.annotation.DistributedTracing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * User: shenzhang
 * Date: 11/7/14
 * Time: 4:25 PM
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private Random random = new Random();

    @Autowired
    private Tracer tracer;
    @Autowired
    private RestTemplate restTemplate;

    @DistributedTracing
    public List<User> getUsers() {
        queryFromDb(2);
        queryFromHttp();

        return Arrays.asList(generateRandomUser());
    }

    @CountMetrics("SayHelloService")
    public String sayHello() {
        return "hello world";
    }

    private void randomError() {
        if (random.nextBoolean()) {
            throw new RuntimeException("Random Error");
        }
    }

    public User generateRandomUser() {
        User user = new User();
        user.setId(new Random().nextInt());
        user.setName("fish");
        user.setAge(32);
        return user;
    }

    private void queryFromDb(int count) {
        if (count == 0) {
            return;
        }

        Span span = tracer.createSpan("queryFromDb" + count);
        LOGGER.info("Query from database...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        queryFromDb(--count);

        tracer.close(span);
    }

    private void queryFromHttp() {
        String url = random.nextBoolean() ? "http://www.baidu.com" : "http://www.sfdsafdas.com";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    }
}
