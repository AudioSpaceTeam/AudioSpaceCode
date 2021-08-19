package com.audiospace.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

//  @Column(nullable = true, length = 300)
//  private String title;

  @Column(nullable = true, length = 300)
  private String body;

  @Column(nullable = true)
  private Integer rating;

  public Review(String body, Integer rating) {
    this.body = body;
    this.rating = rating;
  }

  public Review() {

  }

  public Review(long id, String body, Integer rating) {
    this.id = id;
    this.body = body;
    this.rating = rating;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

//  public String getTitle() {
//    return title;
//  }
//
//  public void setTitle(String title) {
//    this.title = title;
//  }

  public String getBody() {
    return body;
  }

  public User getReviewer() {
    return reviewer;
  }

  public void setReviewer(User reviewer) {
    this.reviewer = reviewer;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  @ManyToOne
  @JoinColumn(name = "reviewer_id")
  private User reviewer;

  @ManyToOne
  @JoinColumn(name = "reviewee_id")
  private User reviewee;


  public User getReviewee() {
    return reviewee;
  }

  public void setReviewee(User reviewee) {
    this.reviewee = reviewee;
  }
}
