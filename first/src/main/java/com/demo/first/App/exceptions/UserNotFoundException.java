package com.demo.first.App.exceptions;

public class UserNotFoundException  extends  RuntimeException{
    public  UserNotFoundException (String message){
        super(message);
    }
}
