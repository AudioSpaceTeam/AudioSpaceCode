package com.audiospace.demo.controllers;

import com.audiospace.demo.models.*;
import com.audiospace.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

  private final UserRepository userDao;
  //  added login properties & passwordEncoder dependency
  private final PasswordEncoder passwordEncoder;
  private final EventRepository eventDao;
  private final ReviewRepository reviewDao;
  private final GenreRepository genreDao;

  public static final DecimalFormat df1 = new DecimalFormat( "#.#" );

  public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, EventRepository eventDao, GenreRepository genreDao, ReviewRepository reviewDao) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
    this.eventDao = eventDao;
    this.genreDao = genreDao;
    this.reviewDao = reviewDao;
  }

  @GetMapping("/register")
  public String showSignupForm(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("hasErrors",false);
    model.addAttribute("errorText","");
    return "register";
  }
// Todo: make it so users cannot share a username/have spaces in their name.
  @PostMapping("/register")
  public String saveUser(@ModelAttribute User user, @RequestParam String isPromoter, Model model) {
    if(!userDao.findAllByUsername(user.getUsername()).isEmpty()){
//      Todo: add an error message to tell user username is taken already.
      model.addAttribute("user",user);
      model.addAttribute("hasErrors",true);
      model.addAttribute("errorText", "The username " + user.getUsername() + " is taken!");
      return "register";
    }
    if(!user.getEmail().contains("@") && !user.getEmail().contains(".")){
      model.addAttribute("user",user);
      model.addAttribute("hasErrors",true);
      model.addAttribute("errorText", "The email " + user.getEmail() + " is invalid!");
      return "register";
    }
    if (isPromoter.equals("true")) {
      user.setPromoter(true);
    } else {
      user.setPromoter(false);
    }
    String hash = passwordEncoder.encode(user.getPassword());
    user.setPassword(hash);
//    Setting their image to our default logo.
    user.setImageUrl("https://cdn.filestackcontent.com/nffA9ioLQOKUoTmpFq50");
    userDao.save(user);
    return "redirect:/login";
  }


  @GetMapping("/profile")
  public String showUserInfo(Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //Logic to get Average Review=======================================================================================
    List<Review> reviews = reviewDao.findAllByRevieweeId(currentUser.getId());
    double total = 0;
    for(Review review: reviews){
      total += review.getRating();
    }
    double ratingAverage = total/reviews.size();
    String newAverage = df1.format(ratingAverage);
    double recentAverage = Double.parseDouble(newAverage);
    if(Double.isNaN(recentAverage)){
      recentAverage = 0;
    }
    //==================================================================================================================



    model.addAttribute("userEvents", userDao.findById(currentUser.getId()).getPromotedEvents());
    model.addAttribute("currentUser", userDao.findById(currentUser.getId()));
    model.addAttribute("user", userDao.findById(currentUser.getId()));
    model.addAttribute("review", new Review());
    model.addAttribute("ratingAverage", recentAverage);
    model.addAttribute("event", new Event());
    model.addAttribute("profileOwner", true);


    return "profile";
  }

  //For submitting review
  @PostMapping("/profile")
  public String submitReview(@ModelAttribute Review review, @RequestParam long userID, @RequestParam long currentUserID) {
    review.setReviewee(userDao.findById(userID));
    review.setReviewer(userDao.findById(currentUserID));
    reviewDao.save(review);
    return "redirect:/profile/" + userID;
  }

  @GetMapping("/profile/{id}")
  public String showUserInfo(@PathVariable long id, Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    Logic to get Average Review=======================================================================================
    List<Review> reviews = reviewDao.findAllByRevieweeId(id);//From path variable
    double total = 0;
    for(Review review: reviews){
      total += review.getRating();
    }
    double ratingAverage = total/reviews.size();
    String newAverage = df1.format(ratingAverage);
    double recentAverage = Double.parseDouble(newAverage);
    if(Double.isNaN(recentAverage)){
      recentAverage = 0;
    }


//    ==================================================================================================================
    model.addAttribute("userEvents", userDao.findById(id).getPromotedEvents());
    model.addAttribute("currentUser", userDao.findById(currentUser.getId()));
//    model.addAttribute("revieweeUser", reviewDao.findById(id).getReviewee());
    model.addAttribute("user", userDao.findById(id));
    model.addAttribute("review", new Review());
    model.addAttribute("ratingAverage", recentAverage);
    model.addAttribute("event", new Event());
    model.addAttribute("profileOwner", id == currentUser.getId());
    return "profile";
  }


  @GetMapping("/profile/{id}/edit")
  public String editUserInfo(@PathVariable long id, Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (currentUser.getId() != userDao.findById(id).getId()) {
      return "redirect:/profile/" + id;
    } else {
      model.addAttribute("user", userDao.findById(id));
      model.addAttribute("genres", genreDao.findAll());

      return "user/edit";


    }
  }

  @PostMapping("/profile/{id}/edit")
  public String saveUserInfo(@ModelAttribute User user,
                             @PathVariable long id,
                             Model model,
                             @RequestParam String isPromoter,
                             @RequestParam String password,
                             @RequestParam String[] genreIds,
                             @RequestParam(name = "fileupload") String url) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (currentUser.getId() != userDao.findById(id).getId()) {
      return "redirect:/profile/" + id;
    } else {
      if (isPromoter.equals("true")) {
        user.setPromoter(true);
      } else {
        user.setPromoter(false);
      }
      if (!passwordEncoder.matches(password, currentUser.getPassword())) {
        return "redirect:/profile/" + id + "/edit";
      }
      String hash = passwordEncoder.encode(password);
      user.setPassword(hash);
//    genre set code below.
      List<Genre> selectedGenres = new ArrayList<>();

      for (String genre : genreIds) {
        if (genre.equalsIgnoreCase("ignore")) {
          continue;
        }
        System.out.println(genre + " Genre id");
//      We are ADDING to the slotted performers list,
//      We are finding the user BY ID
//      We are PARSING the long from the STRING ARRAY, because checkboxes return string arrays.
        selectedGenres.add(genreDao.findGenreByGenreName(genre));
      }
      user.setGenres(selectedGenres);

      user.setImageUrl(url);


      userDao.save(user);
      model.addAttribute("user", userDao.findById(id));
      return "redirect:/profile/";
    }

  }


  @PostMapping("/profile/{id}/delete")
  public String deleteUser(@ModelAttribute User user,
                           @PathVariable long id,
                           @RequestParam String passwordDelete) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (currentUser.getId() != userDao.findById(id).getId()) {
      return "redirect:/profile/" + id;
    } else {
      if (!passwordEncoder.matches(passwordDelete, currentUser.getPassword())) {
        return "redirect:/profile/" + id + "/edit";
      }
      User deleteMe = userDao.findById(currentUser.getId());
      //      TODO: figure out why it isn't cascading correctly but this fixes it for now...
      deleteMe.setSlotted(new ArrayList<>());
      deleteMe.setRequested(new ArrayList<>());
      for (Event event : deleteMe.getPromotedEvents()) {
        event.setPerformers(new ArrayList<>());
        event.setRequesters(new ArrayList<>());
        event.setGenres(new ArrayList<>());
//        event.setPromoter(null);
        eventDao.save(event);
        eventDao.delete(event);
      }
      deleteMe.setPromotedEvents(new ArrayList<>());
      deleteMe.setReviewsGiven(new ArrayList<>());
      deleteMe.setReviewsReceived(new ArrayList<>());
      deleteMe.setGenres(new ArrayList<>());
      userDao.save(deleteMe);
      userDao.deleteById(deleteMe.getId());
      return "redirect:/login";

//      Todo: Ask about where to redirect for logout after deleting a user...
    }

  }
}


//  Come back to this above, to make it check if the user owns the profile or not.
//  If they own it, then IT should display a different welcome message...
//  and enable an edit button?
//  and if not a review button.

