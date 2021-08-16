package com.audiospace.demo.repositories;

import com.audiospace.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    User findByUsername(String username);

    //added delete user option
    @Override
    void delete(User user);

}
