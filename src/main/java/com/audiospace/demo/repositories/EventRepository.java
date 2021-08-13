package com.audiospace.demo.repositories;

import com.audiospace.demo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
  Event findById(long id);

  void deleteById(long id);


  Event findByTitle(String title);
}
