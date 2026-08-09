package com.example.demo;

import com.example.loose.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {

//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(AppConfig.class);
//        GreetingService greetingService=(GreetingService) context.getBean("myBean");//GreetingService.class
//
//        greetingService.sayHello();
//
//       UserService userService=
//               (UserService) context.getBean(UserService.class);
//       userService.notifyUser("Whats up");
//
//        UserService userServiceEmail=
//                (UserService) context.getBean("UserServiceEmail");
//        userServiceEmail.notifyUser("Whats up nigga");
        System.out.println("Starting Spring Application context");
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Retrieving lifecyle Bean");
        LifecycleBean lifecycleBean =context.getBean(LifecycleBean.class);
        lifecycleBean.performTask();

        System.out.println("Closing spring context");

    }
}
