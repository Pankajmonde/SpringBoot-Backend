package com.demo.first.App;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/user")
public class UserController {
    private Map<Integer, User> userDb = new HashMap<>();

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println(user.getEmail());
        userDb.putIfAbsent(user.getId(), user);
        return   ResponseEntity.status(HttpStatus.CREATED)
                .body(user);
    }

    //1->john , john@gmail.com
     @PutMapping
    public  ResponseEntity<User> updateUser(@RequestBody User user) {
        if (! userDb.containsKey(user.getId()))
             //  return  ResponseEntity.notFound().build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            userDb.put(user.getId(), user);
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(user);
            //return ReponseEntity.ok(user);



    }
//   /user/1   /user/2   /user/3
    @DeleteMapping("/{id}")
    public  ResponseEntity <String> deleteUser(@PathVariable int id){
        if(!userDb.containsKey(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        userDb.remove(id);
        return  ResponseEntity.ok("User Deleted");
    }

    @GetMapping
    public List<User> getUser(){
        return new ArrayList<>(userDb.values());
    }
}
