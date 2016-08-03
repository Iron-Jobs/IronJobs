package com.theironyard.controllers;

import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by EddyJ on 8/3/16.
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;


}
