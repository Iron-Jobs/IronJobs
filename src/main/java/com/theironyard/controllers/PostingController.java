package com.theironyard.controllers;

import com.theironyard.command.LocationCommand;
import com.theironyard.command.PostingCommand;
import com.theironyard.entities.Location;
import com.theironyard.entities.Posting;
import com.theironyard.entities.User;
import com.theironyard.exceptions.IdNotFoundException;
import com.theironyard.exceptions.TokenExpiredException;
import com.theironyard.services.LocationRepository;
import com.theironyard.services.MessageRepository;
import com.theironyard.services.PostingRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vasantia on 8/3/16.
 */

@CrossOrigin
@RestController
public class PostingController {

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping(path = "/postings", method = RequestMethod.POST)
    public Posting posting(@RequestHeader(value = "Authorization") String userToken,@RequestBody Posting posting) {
        User user = getUserFromAuth(userToken);
        posting.setOwner(user);
        Location location = locationRepository.findByCity(posting.getLocation().getCity());
        if (location == null){
            location = new Location(posting.getLocation().getCity(), posting.getLocation().getState());
        }
        locationRepository.save(location);
        posting.setLocation(location);
        postingRepository.save(posting);
        return posting;
    }

    @RequestMapping(path = "/postings", method = RequestMethod.GET)
    public List<Posting> showPostings(String salaryStart){
        List<Posting> postingList = postingRepository.findAllByOrderByDateCreatedDesc();
        if(salaryStart != null){
            if (salaryStart.equalsIgnoreCase("Asc")){
                postingList = postingRepository.findAllByOrderBySalaryStartAsc();
            } else if (salaryStart.equalsIgnoreCase("Desc")){
                postingList = postingRepository.findAllByOrderBySalaryStartDesc();
            }
        }
        return postingList;
    }
    
    @RequestMapping(path = "/postings/{id}", method = RequestMethod.GET)
    public Posting showSinglePosting(@PathVariable Integer id) {

        return postingRepository.findOne(id);
    }

    @RequestMapping(path = "/postings/filter/{salaryStart}", method = RequestMethod.GET)
    public List<Posting> filterBySalary(@PathVariable int salaryStart){
        List<Posting> postingList = postingRepository.findAllBySalaryStartGreaterThanEqual(salaryStart);
        return postingList;
    }

    @RequestMapping(path = "postings/filter/location/{id}",method = RequestMethod.GET)
    public List<Posting> filterByLocation(@PathVariable int id){
        Location location = locationRepository.findOne(id);
        List<Posting> postingList = postingRepository.findAllByLocation(location);
        return postingList;
    }

//    @RequestMapping(path = "/postings/search", method = RequestMethod.GET)
//    public List<Posting> searchByTitleAndLocation(String title, Location location){
//        List<Posting> postingList = postingRepository.findAllByOrderByDateCreatedDesc();
//
//        if(title != null && location != null){
//            postingList = postingRepository.findAllByTitleContainingAndLocationContaining(title, location);
//        }
//        else if(location != null) {
//            postingList = postingRepository.findAllByLocationContaining(location);
//        }
//        else if(title != null){
//            postingList = postingRepository.findAllByTitleContaining(title);
//        }
//        return postingList;
//    }


    @RequestMapping(path = "postings/searches/title/{title}", method = RequestMethod.GET)
    public List<Posting> searchByTitle(@PathVariable String title){
        List<Posting> postingList = postingRepository.findAllByTitleContaining(title);
        return postingList;
    }

    @RequestMapping(path = "/postings/{id}", method = RequestMethod.PUT)
    public Posting updatePosting(@PathVariable Integer id, @RequestBody PostingCommand command) {

        Posting updatePosting = postingRepository.findOne(id);
        if (updatePosting == null) {
            throw new IdNotFoundException();
        }

        updatePosting.setTitle(command.getTitle());
        updatePosting.setDescription(command.getDescription());
        updatePosting.setSalaryStart(command.getSalaryStart());
        updatePosting.setSalaryEnd(command.getSalaryEnd());
        postingRepository.save(updatePosting);

        return updatePosting;
    }

    @RequestMapping(path = "/postings/{id}", method = RequestMethod.DELETE)
    public void deletePosting(@PathVariable int id) {

        Posting deletePosting = postingRepository.findOne(id);
        if (deletePosting == null) {
            throw new IdNotFoundException();
        }
        postingRepository.delete(id);

    }
    public User getUserFromAuth(String userToken){
        User user = userRepository.findFirstByToken(userToken.split(" ")[1]);
        if(!user.isTokenValid()){
            throw new TokenExpiredException();
        }
        return user;
    }
}
