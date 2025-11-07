package com.hms.HospitalManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    // Catch all routes except those containing a dot (like .js, .css)
    @RequestMapping(value = "/")
    public String redirect() {
        return "forward:/browser/index.html";
    }
}
