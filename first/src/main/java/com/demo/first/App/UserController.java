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
    //  /user/100, user/400 . user/1
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable(value ="userId", required = false)  int id){
        if(!userDb.containsKey(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return  ResponseEntity.ok(userDb.get(id));
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<User> getUser(
            @PathVariable("userId")  int id,
            @PathVariable int orderId
    ){
        System.out.println("Order id"+orderId);
        if(!userDb.containsKey(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return  ResponseEntity.ok(userDb.get(id));
    }
       // /search?name=pankaj
       @GetMapping("/search")
       public ResponseEntity<List<User>> searchUsers(
               @RequestParam(required = false, defaultValue = "lily") String name,
               @RequestParam(required = false, defaultValue = "email") String email
       ) {
           System.out.println(name);

           List<User> users = userDb.values().stream()
                   .filter(u -> u.getName().equalsIgnoreCase(name))
                   .filter(u -> u.getEmail().equalsIgnoreCase(email))
                   .toList();

           return ResponseEntity.ok(users);
       }
    @GetMapping("/info")
    public String getInfo(@RequestHeader ("User-Agent") String userAgent) {
        return "User Agent:"+userAgent;
    }
}


