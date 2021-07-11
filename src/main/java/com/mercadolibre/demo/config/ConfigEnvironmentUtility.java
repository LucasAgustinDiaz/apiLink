package com.mercadolibre.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigEnvironmentUtility {

    private Environment env;

    public ConfigEnvironmentUtility(Environment env) {
        this.env = env;
    }

    public String getProperty(String pPropertyKey) {
        return env.getProperty(pPropertyKey);
    }
}