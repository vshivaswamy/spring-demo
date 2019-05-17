package io.vinays.cassandra.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
    public String getHealthCheck(){
        return "{ \"isWorking\" : true }";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        Iterable<User> result = userRepository.findAll();
        List<User> users = new ArrayList<>();
        result.forEach(users::add);
        return users;
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable String id){
        UUID uuid = UUID.fromString(id);
        Optional<User> user = userRepository.findById(uuid);
        return user;
    }

    @PutMapping("/user/{id}")
    public Optional<User> updateUser(@RequestBody User newUser, @PathVariable String id){
        UUID uuid = UUID.fromString(id);
        Optional<User> optionalUser = userRepository.findById(uuid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            userRepository.save(user);
        }
        return optionalUser;
    }

    @DeleteMapping(value = "/user/{id}", produces = "application/json; charset=utf-8")
    public String deleteUser(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        Boolean result = userRepository.existsById(uuid);
        userRepository.deleteById(uuid);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User newUser){
        User user = new User(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail());
        userRepository.save(user);
        return user;
    }


}
