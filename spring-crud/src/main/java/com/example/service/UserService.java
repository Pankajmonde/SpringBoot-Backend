package service;

import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void addUser(String name){
        userRepository.save(name);

    }
    public List<String> getAlluser(){
        return userRepository.findAll();

    }

}
