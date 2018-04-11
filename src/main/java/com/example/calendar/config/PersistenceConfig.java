package com.example.calendar.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"com.example.calendar.entities", "org.springframework.data.jpa.convert.threeten"})
public class PersistenceConfig {
}
