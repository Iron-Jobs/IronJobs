package com.theironyard.controllers;

import com.theironyard.command.UserCommand;
import com.theironyard.entities.Posting;
import com.theironyard.entities.User;
import com.theironyard.exceptions.LoginFailedException;
import com.theironyard.exceptions.TokenExpiredException;
import com.theironyard.exceptions.UserNotFoundException;
import com.theironyard.services.LocationRepository;
import com.theironyard.services.PostingRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * Created by EddyJ on 8/3/16.
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    LocationRepository locationRepository;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    }
    @RequestMapping(path = "/users/{id}",method = RequestMethod.GET)
    public User getUser(@RequestHeader(value = "Authorization") String userToken, @PathVariable Integer id){
        getUserFromAuth(userToken);
        User user = userRepository.findOne(id);
        return user;
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public User createUser(@RequestBody UserCommand userCommand) throws PasswordStorage.CannotPerformOperationException {
        User user = userRepository.findByUsername(userCommand.getUsername());
        if (user == null){
            user = new User(userCommand.getUsername(), PasswordStorage.createHash(userCommand.getPassword()));
            userRepository.save(user);
        }
        return user;
    }

    @RequestMapping(path = "/token", method = RequestMethod.POST)
    public Map getToken(@RequestBody UserCommand userCommand) throws Exception {
        User user = checkLogin(userCommand);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("token", user.getToken());
        return tokens;
    }
    @RequestMapping(path = "/token/regenerate",method = RequestMethod.PUT)
    public String regenerateToken(@RequestBody UserCommand userCommand) throws Exception{
        User user = checkLogin(userCommand);
        return user.regenerate();
    }

    public User checkLogin(UserCommand userCommand) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        User user = userRepository.findByUsername(userCommand.getUsername());
        if(user == null){
            throw new UserNotFoundException();
        }
        if(!PasswordStorage.verifyPassword(userCommand.getPassword(), user.getPassword())){
            throw new LoginFailedException();
        }
        if(user.getExpiration().isBefore(LocalDateTime.now())){
            throw new TokenExpiredException();
        }
        return user;
    }

    public User getUserFromAuth(String userToken){
        User user = userRepository.findFirstByToken(userToken.split(" ")[1]);
        if(!user.isTokenValid()){
            throw new TokenExpiredException();
        }
        return user;
    }

}
