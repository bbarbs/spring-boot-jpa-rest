package com.jparest.config;

import com.monitorjbl.json.JsonViewSupportFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public JsonViewSupportFactoryBean jsonViewSupportFactoryBean() {
        return new JsonViewSupportFactoryBean();
    }
}
