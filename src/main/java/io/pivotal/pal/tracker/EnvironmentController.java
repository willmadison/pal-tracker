package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvironmentController {

    @Autowired
    private EnvironmentConfiguration config;

    @GetMapping("/env")
    public Map<String, String> env() {
        HashMap<String, String> env = new HashMap<>();

        env.put("PORT", config.getPort());
        env.put("MEMORY_LIMIT", config.getMemoryLimit());
        env.put("CF_INSTANCE_INDEX", config.getCfInstanceIndex());
        env.put("CF_INSTANCE_ADDR", config.getCfInstanceAddress());

        return env;
    }
}
