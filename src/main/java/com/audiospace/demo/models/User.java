package com.audiospace.demo.models;

import javax.persistence.*;
import java.util.List;


// Below allows us to the Dependency for creating our users table dynamically without needed to insert it into the console.
@Entity
@Table(name = "users")
public class User {

  //  This ID is going to be the MAIN identifier, that is in this class.
//  Database understands it will be auto incremented inside of mysql.
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  //  Below sets up parameters for our column in the table in the DB.
  @Column(nullable = false, length = 25)
  private String username;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String displayName;

  //  Password
  @Column(nullable = false)
  private String password;

//  Profile Image? -- we can add later.

  //  Bio
  @Column(nullable = true, length = 500)
  private String bio;

  public List<Event> getSlotted() {
    return slotted;
  }

  public void setSlotted(List<Event> slotted) {
    this.slotted = slotted;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  //  Is promoter bool
  @Column(nullable = false)
  private Boolean isPromoter;

  // Sets the events to the promoter
//  Below says 1 user to many events...
//  Cascade is when the user gets deleted, all of it's associated ads would also get deleted.
//  We are then mapping this under the user
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "promoter")
  private List<Event> promotedEvents;
//  Above is now a reference to all the events the user should have associated with it?


  @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewer")
  private List<Review> reviewsGiven;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewer")
  private List<Review> reviewsReceived;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "users_events",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "event_id")}
//    )

  @ManyToMany(mappedBy = "performers")
  private List<Event> slotted;


  public List<Review> getReviewsGiven() {
    return reviewsGiven;
  }

  //  Many to many genres
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "users_genres",
    joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "genre_id")}
  )
  private List<Genre> genres;


  public void setReviewsGiven(List<Review> reviewsGiven) {
    this.reviewsGiven = reviewsGiven;
  }

  public List<Review> getReviewsReceived() {
    return reviewsReceived;
  }

  public void setReviewsReceived(List<Review> reviewsReceived) {
    this.reviewsReceived = reviewsReceived;
  }

  public User(long id, String username, String email, String displayName, String password, String bio, Boolean isPromoter, List<Event> promotedEvents) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.displayName = displayName;
    this.password = password;
    this.bio = bio;
    this.isPromoter = isPromoter;
    this.promotedEvents = promotedEvents;
  }

  public List<Event> getPromotedEvents() {
    return promotedEvents;
  }

  public void setPromotedEvents(List<Event> promotedEvents) {
    this.promotedEvents = promotedEvents;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public Boolean getPromoter() {
    return isPromoter;
  }

  public void setPromoter(Boolean promoter) {
    isPromoter = promoter;
  }

  public User() {

  }

  //added authentication model
  public User(User copy) {
    id = copy.id; // This line is SUPER important! Many things won't work if it's absent
    email = copy.email;
    username = copy.username;
    password = copy.password;
    displayName = copy.displayName;
    bio = copy.bio;
    isPromoter = copy.isPromoter;
    promotedEvents = copy.promotedEvents;
  }

  public User(long id, String username, String email, String displayName, String password, String bio, Boolean isPromoter) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.displayName = displayName;
    this.password = password;
    this.bio = bio;
    this.isPromoter = isPromoter;
  }

  public User(String username, String email, String displayName, String password, String bio, Boolean isPromoter) {
    this.username = username;
    this.email = email;
    this.displayName = displayName;
    this.password = password;
    this.bio = bio;
    this.isPromoter = isPromoter;
  }

}
