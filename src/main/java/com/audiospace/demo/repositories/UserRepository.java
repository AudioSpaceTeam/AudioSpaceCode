package com.audiospace.demo.repositories;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    User findByUsername(String username);

    List<User> findAllBySlotted(Event slotted);
}
