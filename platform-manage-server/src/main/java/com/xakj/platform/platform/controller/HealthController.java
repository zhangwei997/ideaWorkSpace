package com.xakj.platform.platform.controller;

import com.xakj.platform.filter.HttpResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/health")
public class HealthController {

    @RequestMapping("/info")
    public HttpResponse info(){
        return new HttpResponse("Ok,I am online.");
    }
}
