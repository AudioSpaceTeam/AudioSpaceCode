package com.audiospace.demo.repositories;

import com.audiospace.demo.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
