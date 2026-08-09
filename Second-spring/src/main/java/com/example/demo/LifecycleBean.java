package com.example.demo;


import com.example.loose.NotificationService;
import org.springframework.stereotype.Component;



public class LifecycleBean {

    private NotificationService notificationService;

    public LifecycleBean(NotificationService notificationService) {
        System.out.println("Contrustor Called: Dependency Injected");
        this.notificationService = notificationService;
    }

    public void init(){
        System.out.println("@init called : Bean initialized");
        notificationService.send("Hello from init()");
    }
    public void performTask(){
        System.out.println("Ready to use");

    }
    public void cleanUp(){
        System.out.println("Cleanup() being called" );

    }

}
