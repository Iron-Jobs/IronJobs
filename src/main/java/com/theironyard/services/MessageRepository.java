package com.theironyard.services;

import com.theironyard.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EddyJ on 8/6/16.
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
