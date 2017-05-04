package com.github.shenzhang.tracing.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Zhang Shen
 * Date: 5/4/17
 * Time: 11:40 PM.
 */
@Controller
public class MonitorController {
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
}
