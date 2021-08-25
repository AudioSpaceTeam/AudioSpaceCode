package com.audiospace.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    //  This ID is going to be the MAIN identifier, that is in this class.
//  Database understands it will be auto incremented inside of mysql.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //  Below sets up parameters for our column in the table in the DB.
    @Column(nullable = false, length = 40)
    private String title;

    //  Below sets up parameters for our column in the table in the DB.
    @Column(nullable = false, length = 500)
    private String description;

    //  Max BAnds INT
    @Column(nullable = false)
    private Integer slots;

    //  Location String (for reverse geocoding)
    @Column(nullable = false, length = 100)
    private String location;

//  Event flyer image
//  Not yet...

//  //  DATETIME DATETIME
//@Column
//@Temporal(TemporalType.TIMESTAMP)
//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//private Date dateTime;
//
//  public Date getDateTime() {
//    return dateTime;
//  }
//
//  public void setDateTime(Date dateTime) {
//    this.dateTime = dateTime;
//  }

    @Column(name = "startDateTime", columnDefinition = "TIMESTAMP")
    private LocalDateTime startDateTime;

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getDate(){
      String dateTime = startDateTime.toString();
      String[] dateTimeArray = dateTime.split("T");

      String date = dateTimeArray[0];
      return date;
    }

  public String getDateFormat(){
    String dateTime = startDateTime.toString();
    String[] dateTimeArray = dateTime.split("T");
    String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    String date = dateTimeArray[0];
    String[] dateArray = date.split("-");
    int monthNumber = Integer.parseInt(dateArray[1]) - 1;
    String month = months[monthNumber];
    String result = month + " " + dateArray[2] + " " + dateArray[0];
    return result;
  }

  public String getTime(){
    String dateTime = startDateTime.toString();
    String[] dateTimeArray = dateTime.split("T");

    String time = dateTimeArray[1];
    return time;
  }


    //  price double
    @Column(nullable = true)
    private Double price;

    //  Promoter ID FK
    // Establishes that there's going to be multiple events tied back to One user.
    // Join column binds the relationship together?
    @ManyToOne
    @JoinColumn(name = "promoter_id")
    private User promoter;
    //Many to many with user's performing or "slotted"
//    @ManyToMany(mappedBy = "slotted")


//TODO: refer to Jay's video for how to set this up. with Confirmed vs unconfirmed.
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "events_performers",
    joinColumns = {@JoinColumn(name = "event_id")},
    inverseJoinColumns = {@JoinColumn(name = "user_id")}
  )
  private List<User> performers;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "events_requesters",
    joinColumns = {@JoinColumn(name = "event_id")},
    inverseJoinColumns = {@JoinColumn(name = "user_id")}
  )
  private List<User> requesters;

  //Should be many to many with genres
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "events_genres",
          joinColumns = {@JoinColumn(name = "event_id")},
          inverseJoinColumns = {@JoinColumn(name = "genre_id")}
  )
  private List<Genre> genres;


  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public Event() {

  }

  public Event(long id, String title, String description, Integer slots, String location, Double price,
               User promoter, LocalDateTime startDateTime) {

    this.id = id;
    this.title = title;
    this.description = description;
    this.slots = slots;
    this.location = location;
    this.price = price;
    this.promoter = promoter;
    this.startDateTime = startDateTime;

  }

  public Event(String title, String description, Integer slots, String location, Double price, User promoter) {
    this.title = title;
    this.description = description;
    this.slots = slots;
    this.location = location;
    this.price = price;
    this.promoter = promoter;
  }

  public Event(String title, String description, Integer slots, String location, Double price) {
    this.title = title;
    this.description = description;
    this.slots = slots;
    this.location = location;
    this.price = price;
  }

  public Event(long id, String title, String description, Integer slots, String location, Double price) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.slots = slots;
    this.location = location;
    this.price = price;
  }

  public Event(long id, String title, String description, String location) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.location = location;

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getSlots() {
    return slots;
  }

  public void setSlots(Integer slots) {
    this.slots = slots;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }


//  public Date getDatetime() {
//    return datetime;
//  }
//
//  public void setDatetime(Date datetime) {
//    this.datetime = datetime;
//  }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getPromoter() {
        return promoter;
    }

    public void setPromoter(User promoter) {
        this.promoter = promoter;
    }


  public List<User> getPerformers() {
    return performers;
  }

  public void setPerformers(List<User> performers) {
    this.performers = performers;
  }


  public List<User> getRequesters() {
    return requesters;
  }

  public void setRequesters(List<User> requesters) {
    this.requesters = requesters;
  }

//  bands sloted?
//    @ManyToMany(mappedBy = "performers")
//    private List<User> performers;



}

