package com.example.config;

import com.example.db.DatabaseConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.xml.crypto.Data;

@Configuration
@ComponentScan("com.example")
public class AppConfig {

@Bean(initMethod = "init",destroyMethod = "cleanup")
    public DatabaseConnection dbconnection() {
    return new DatabaseConnection();
}

}
