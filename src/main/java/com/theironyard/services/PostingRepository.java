package com.theironyard.services;

import com.theironyard.entities.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EddyJ on 8/3/16.
 */
public interface PostingRepository extends JpaRepository<Posting, Integer> {
}
