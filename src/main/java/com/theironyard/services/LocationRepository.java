package com.theironyard.services;

import com.theironyard.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EddyJ on 8/3/16.
 */
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
