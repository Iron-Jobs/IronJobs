package com.theironyard.controllers;

import com.theironyard.command.PostingCommand;
import com.theironyard.entities.Posting;
import com.theironyard.exceptions.IdNotFoundException;
import com.theironyard.services.LocationRepository;
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

    @RequestMapping(path = "/postings", method = RequestMethod.POST)
    public Posting posting(@RequestBody Posting posting) {
        postingRepository.save(posting);
        return posting;
    }

    @RequestMapping(path = "/postings", method = RequestMethod.GET)
    public List<Posting> showPostings(){

        return postingRepository.findByIdOrderByDateCreatedDesc();
    }
    
    @RequestMapping(path = "/postings/{id}", method = RequestMethod.GET)
    public Posting showSinglePosting(@PathVariable Integer id) {

        return postingRepository.findOne(id);
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
}
