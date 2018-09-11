package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeConfiguration welcomeConfiguration;


    @GetMapping("/")
    public String hello() {
        return welcomeConfiguration.getMessage();
    }
}
