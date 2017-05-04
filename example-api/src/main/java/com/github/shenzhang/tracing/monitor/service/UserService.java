package com.github.shenzhang.tracing.monitor.service;

import com.github.shenzhang.tracing.monitor.domain.User;
import com.github.shenzhang.tracing.agent.annotation.Trace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

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

    @Autowired
    private Tracer tracer;

    @Trace
    public List<User> getUsers() {
        queryFromDb();
        return Arrays.asList(generateRandomUser());
    }

    @Trace
    public User generateRandomUser() {
        User user = new User();
        user.setId(new Random().nextInt());
        user.setName("fish");
        user.setAge(32);
        return user;
    }

    private void queryFromDb() {
        Span span = tracer.createSpan("queryFromDb");
        LOGGER.info("Query from database...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tracer.close(span);
    }
}
