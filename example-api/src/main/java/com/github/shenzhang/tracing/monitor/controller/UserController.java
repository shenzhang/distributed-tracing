package com.github.shenzhang.controller;

import com.github.shenzhang.tracing.monitor.domain.User;
import com.github.shenzhang.tracing.monitor.service.UserService;
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
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
