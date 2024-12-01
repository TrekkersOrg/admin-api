package com.strive.adminapi.admin_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EnvironmentController {

    @GetMapping("/environment")
    public Map<String, String> getEnvironmentVariables() {
        // Fetch all environment variables as a Map
        return System.getenv();
    }
}
