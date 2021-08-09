package com.audiospace.demo.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository <EventModel, Long> {
}
