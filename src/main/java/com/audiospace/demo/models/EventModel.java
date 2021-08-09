package com.audiospace.demo.models;
import javax.persistence.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Title
    @Column(nullable = false, length = 160)
    private String title;

    //Description
    @Column(nullable = false, length = 500)
    private String description;

    @Column
    private int maxBands;

    @Column
    private String location;

//    @Column
//    private eventImage eventImage;

    @Column
    private String promoter;

    @Column
    private String date;

    @Column
    private int time;

    @Column
    private int price;

    public EventModel() {
    }



}
