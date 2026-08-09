package com.example.loose;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SmsEmailNotificationService implements  NotificationService{
    @Override
    public  void send(String message){
        System.out.println("SMS  "+message);

    }
}
