package com.tarpitout.aio.frameworks.springboot.controller;

import com.tarpitout.aio.frameworks.springboot.service.AIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AIOController {
    @Autowired
    private AIOService aioService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return aioService.greet();
    }
}
