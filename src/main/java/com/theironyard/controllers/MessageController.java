package com.theironyard.controllers;

import com.theironyard.entities.Message;
import com.theironyard.entities.User;
import com.theironyard.exceptions.TokenExpiredException;
import com.theironyard.services.LocationRepository;
import com.theironyard.services.MessageRepository;
import com.theironyard.services.PostingRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vasantia on 8/6/16.
 */

@RestController
@CrossOrigin
public class MessageController {

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    MessageRepository messageRepository;

//    @RequestMapping(path = "/messages", method = RequestMethod.POST)
//    public Message message(@RequestHeader(value = "Authorization") String userToken, @RequestBody Message message){
//
//        User user = getUserFromAuth(userToken);
//        message.setApplicants();
//
//        messageRepository.save(message);
//        return message;
//    }


    public User getUserFromAuth(String userToken){
        User user = userRepository.findFirstByToken(userToken.split(" ")[1]);
        if(!user.isTokenValid()){
            throw new TokenExpiredException();
        }
        return user;
    }
}
