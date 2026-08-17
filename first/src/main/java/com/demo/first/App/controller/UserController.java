package com.demo.first.App.controller;


import com.demo.first.App.Model.User;
import com.demo.first.App.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/user")
public class UserController {

    private UserService userService =new UserService();



    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User  createdUser=userService.createUser(user);
        return  new  ResponseEntity<>(createdUser ,HttpStatus.CREATED);

    }

    //1->john , john@gmail.com
     @PutMapping
    public  ResponseEntity<User> updateUser(@RequestBody User user) {
          User updated= userService.updateuser(user);
          if(updated == null)
              return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.ok(updated);



    }
//   /user/1   /user/2   /user/3
    @DeleteMapping("/{id}")
    public  ResponseEntity <String> deleteUser(@PathVariable int id){
        boolean isDeleted =userService.deleteUser(id);
        if(!isDeleted)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return  ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<User> getUser(){
        return userService.getAllusers();

    }
    //  /user/100, user/400 . user/1
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(
            @PathVariable(value ="userId", required = false)  int id){
       User user=userService.getUserById(id);
       if(user == null)
               return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return  ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<User> getUser(
            @PathVariable("userId")  int id,
            @PathVariable int orderId
    ){

        User user=userService.getUserById(id);
        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return  ResponseEntity.ok(user);
    }
       // /search?name=pankaj
       @GetMapping("/search")
       public ResponseEntity<List<User>> searchUsers(
               @RequestParam(required = false, defaultValue = "lily") String name,
               @RequestParam(required = false, defaultValue = "email") String email
       ) {

           return  ResponseEntity.ok(userService.searchUsers(name,email));
       }
    @GetMapping("/info/{id}")

    public String getInfo(
            @PathVariable int id,
            @RequestParam String name,
            @RequestHeader ("User-Agent") String userAgent) {
        return "User Agent:"+userAgent
                + "  : "+id
                + "  : "+name;
    }

}


