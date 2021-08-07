package com.audiospace.demo.models;

import javax.persistence.*;
import java.util.Date;

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

}
