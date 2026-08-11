package com.demo.first;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartUpRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application has started using commandline runner ");

    }
}
