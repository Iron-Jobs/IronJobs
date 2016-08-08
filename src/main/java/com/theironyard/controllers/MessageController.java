package com.theironyard.controllers;

import com.theironyard.command.MessageCommand;
import com.theironyard.entities.Message;
import com.theironyard.entities.Posting;
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

    @RequestMapping(path = "/postings/{id}/messages", method = RequestMethod.POST)
    public Posting addMessage(@RequestHeader(value = "Authorization") String userToken, @PathVariable int id,
                              @RequestBody MessageCommand command) throws Exception {
        User user = getUserFromAuth(userToken);
        Posting posting = postingRepository.findOne(id);
        if(posting == null){
            throw new Exception("Posting doesn't exist");
        }
        Message message = new Message(command.getText());
        message.setOwner(user);
        messageRepository.save(message);

        posting.addMessage(message);
        postingRepository.save(posting);

        return posting;
    }

    @RequestMapping(path = "/messages/{id}/reply", method = RequestMethod.POST)
    public Message replyMessage(@RequestHeader(value = "Authorization") String userToken, @PathVariable int id,
                                @RequestBody MessageCommand command) throws Exception {

        User user = getUserFromAuth(userToken);
        Message originalMessage = messageRepository.findOne(id);
        if(originalMessage == null){
            throw new Exception("Message doesn't exist");
        }
        Message replyMessage = new Message(command.getText());
        replyMessage.setOwner(user);
        messageRepository.save(replyMessage);

        originalMessage.addReply(replyMessage);
        messageRepository.save(originalMessage);

        return originalMessage;
    }

    public User getUserFromAuth(String userToken){
        User user = userRepository.findFirstByToken(userToken.split(" ")[1]);
        if(!user.isTokenValid()){
            throw new TokenExpiredException();
        }
        return user;
    }
}
