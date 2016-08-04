package com.theironyard.services;

import com.theironyard.entities.Posting;
import com.theironyard.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by EddyJ on 8/3/16.
 */
public interface PostingRepository extends JpaRepository<Posting, Integer> {

//    List<Posting> findFirstByIdOrderByDateCreatedDesc();

   //Collection<Posting> findByApplicants(User user);
}
