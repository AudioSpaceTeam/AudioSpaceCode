package com.audiospace.demo.repositories;

import com.audiospace.demo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
  Event findById(long id);

  List<Event> findAllByTitleContainingOrDescriptionContaining(String title, String description);

  void deleteById(long id);


  Event findByTitle(String title);
}
