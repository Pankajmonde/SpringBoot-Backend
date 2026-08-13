package com.demo.first;

import com.demo.first.App.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/Hello")
    public String sayHello(){
        return "Hello world";
    }
      @GetMapping("/user")
    //@RequestMapping(value="/user",method= RequestMethod.GET)
    public User getUser(){
        User user =new User(1, "john","pankaj@gmail.com");
        return  user;
      }

}
