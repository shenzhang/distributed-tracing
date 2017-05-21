package com.github.shenzhang.monitor.server.controller;

import com.github.shenzhang.monitor.agent.annotation.CountMetrics;
import com.github.shenzhang.monitor.server.domain.User;
import com.github.shenzhang.monitor.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Zhang Shen
 * Date: 2/8/17
 * Time: 3:31 PM.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    @CountMetrics("GetUser")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping("/hello")
    public String hello() {
        return userService.sayHello();
    }
}
