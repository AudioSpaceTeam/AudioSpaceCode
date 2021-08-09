package com.audiospace.demo.models;

import javax.persistence.*;

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
//  @ManyToMany
//  @JoinColumn(name = "event_id")
//  private Event event;

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
