package com.audiospace.demo.models;

import javax.persistence.*;
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
  @Column(nullable = false, length = 500)
  private String bio;

//  Is promoter bool
  @Column(nullable = false)
  private Boolean isPromoter;


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

  public User(String username, String email, String displayName, String password) {
    this.username = username;
    this.email = email;
    this.displayName = displayName;
    this.password = password;
  }

}
