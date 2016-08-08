package com.theironyard.services;

import com.theironyard.entities.Location;
import com.theironyard.entities.Posting;
import com.theironyard.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by EddyJ on 8/3/16.
 */
public interface PostingRepository extends JpaRepository<Posting, Integer> {

    List<Posting> findByOwner(User user);
    List<Posting> findByApplicants(User user);
    List<Posting> findAllByOrderByDateCreatedDesc();
    List<Posting> findAllByOrderBySalaryStartAsc();
    List<Posting> findAllByOrderBySalaryStartDesc();
    List<Posting> findAllBySalaryStartGreaterThan(Integer salaryStart);
    List<Posting> findAllByLocationContaining(Location location);
    List<Posting> findAllBySalaryStartGreaterThanAndLocationContaining(Integer salaryStart, Location location);
    List<Posting> findAllByTitleContainingAndLocationContaining(String title, Location location);
    List<Posting> findAllByTitleContaining(String title);
    List<Posting> findAllByLocation(Location location);
    List<Posting> findAllByLocationContaining(String city, String state);
}
