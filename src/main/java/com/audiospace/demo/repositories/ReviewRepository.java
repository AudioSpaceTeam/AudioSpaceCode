package com.audiospace.demo.repositories;

import com.audiospace.demo.models.Review;
import com.audiospace.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Review findById(long id);

    List<Review> findAllByRevieweeId(long id);
    //Added this comment to push lol//Added this comment to push lol//Added this comment to push lol
}
