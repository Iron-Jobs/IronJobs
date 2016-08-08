package com.theironyard.controllers;

import com.theironyard.entities.Location;
import com.theironyard.services.LocationRepository;
import com.theironyard.services.MessageRepository;
import com.theironyard.services.PostingRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by EddyJ on 8/5/16.
 */
@CrossOrigin
@RestController
public class LocationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping(path = "/locations", method = RequestMethod.GET)
    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    @RequestMapping(path = "/locations/{city}/{state}", method = RequestMethod.GET)
    public Location getLocationId(@PathVariable String city, @PathVariable String state){
        Location location = locationRepository.findByCityAndState(city, state);
        return location;
    }
}
