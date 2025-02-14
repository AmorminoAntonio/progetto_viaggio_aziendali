package com.example.progettoSettimanaleSpringWebData.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class Cloudinary {
    @Bean
    public com.cloudinary.Cloudinary uploader() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dvnmec9ie");
        config.put("api_key", "344699519273455");
        config.put("api_secret", "5b35iMkqJF584m7T3HXauOenX58");
        return new com.cloudinary.Cloudinary(config);
    }
}

