package com.audiospace.demo.models;

import javax.persistence.*;
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

  //  DATETIME DATETIME
  @Column(nullable = false)
  private Date datetime;

  //  price double
  @Column(nullable = false)
  private Double price;

  //  Promoter ID FK
  // Establishes that there's going to be multiple events tied back to One user.
  // Join column binds the relationship together?
  @ManyToOne
  @JoinColumn(name = "promoter_id")
  private User promoter;

//Should be many to many with genres
//  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "genre")
//  private List<Genre> genres;



  public Event(){

  }

  public Event(long id, String title, String description, Integer slots, String location, Date datetime, Double price, User promoter) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.slots = slots;
    this.location = location;
    this.datetime = datetime;
    this.price = price;
    this.promoter = promoter;
  }

  public Event(String title, String description, Integer slots, String location, Date datetime, Double price, User promoter) {
    this.title = title;
    this.description = description;
    this.slots = slots;
    this.location = location;
    this.datetime = datetime;
    this.price = price;
    this.promoter = promoter;
  }

  public Event( String title, String description, Integer slots, String location, Date datetime, Double price) {
    this.title = title;
    this.description = description;
    this.slots = slots;
    this.location = location;
    this.datetime = datetime;
    this.price = price;
  }

  public Event(long id, String title, String description, Integer slots, String location, Date datetime, Double price) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.slots = slots;
    this.location = location;
    this.datetime = datetime;
    this.price = price;
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

  public Date getDatetime() {
    return datetime;
  }

  public void setDatetime(Date datetime) {
    this.datetime = datetime;
  }

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

  //  bands sloted?
//    @ManyToMany(mappedBy = "performers")
//    private List<User> performers;


}
