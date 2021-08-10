package com.audiospace.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
public class Genre {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  //  Below sets up parameters for our column in the table in the DB.
  @Column(nullable = false, length = 25)
  private String genreName;

//  Many to many with events
@ManyToMany(mappedBy = "genres")
private List<Event> events;

  //  Many to many with users
  @ManyToMany(mappedBy = "genres")
  private List<User> users;

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  public Genre(String genreName) {
    this.genreName = genreName;
  }

  public Genre() {
  }

  public Genre(long id, String genreName) {
    this.id = id;
    this.genreName = genreName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getGenreName() {
    return genreName;
  }

  public void setGenreName(String genreName) {
    this.genreName = genreName;
  }

//    @ManyToOne
//  @JoinColumn(name = "reviewEvent")
//  private Event reviewEvent;

  //    @ManyToOne
//  @JoinColumn(name = "reviewUser")
//  private Event reviewUser;
}
