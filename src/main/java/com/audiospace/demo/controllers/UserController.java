package com.audiospace.demo.controllers;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.models.Genre;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.EventRepository;
import com.audiospace.demo.repositories.GenreRepository;
import com.audiospace.demo.repositories.ReviewRepository;
import com.audiospace.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    return "register";
  }

  @PostMapping("/register")
  public String saveUser(@ModelAttribute User user, @RequestParam String isPromoter) {
    if (isPromoter.equals("true")) {
      user.setPromoter(true);
    } else {
      user.setPromoter(false);
    }
    String hash = passwordEncoder.encode(user.getPassword());
    user.setPassword(hash);
    userDao.save(user);
    return "redirect:/login";
  }

  @GetMapping("/profile")
  public String showUserInfo( Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    model.addAttribute("userEvents", userDao.findById(currentUser.getId()).getPromotedEvents());
    model.addAttribute("currentUser", userDao.findById(currentUser.getId()));
    model.addAttribute("user", userDao.findById(currentUser.getId()));
    model.addAttribute("review", new Review());
    model.addAttribute("event", new Event());
    model.addAttribute("profileOwner", true);
    return "profile";
  }

  //For submitting review
  @PostMapping("/profile")
  public String submitReview(@ModelAttribute Review review, @RequestParam long userID, @RequestParam long currentUserID){
    review.setReviewee(userDao.findById(userID));
    review.setReviewer(userDao.findById(currentUserID));
    reviewDao.save(review);
    return "redirect:/profile/" + userID;
  }

  @GetMapping("/profile/{id}")
  public String showUserInfo(@PathVariable long id, Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    model.addAttribute("userEvents", userDao.findById(id).getPromotedEvents());
    model.addAttribute("currentUser", userDao.findById(currentUser.getId()));
    model.addAttribute("user", userDao.findById(id));
    model.addAttribute("review", new Review());
    model.addAttribute("profileOwner", id == currentUser.getId());
//    model.addAttribute("review", new Review());

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

    @GetMapping("/profile/{id}")
    public String showUserInfo(@PathVariable long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userEvents", userDao.findById(id).getPromotedEvents());
        model.addAttribute("user", userDao.findById(id));
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
                               @RequestParam String[] genreIds) {
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
//        System.out.println(currentUser.getPassword());
//        System.out.println(password);
//        System.out.println(passwordEncoder.matches(currentUser.getPassword(), password));
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

            userDao.save(user);
            model.addAttribute("user", userDao.findById(id));
            return "redirect:/profile/";
        }

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
      for(Event event: deleteMe.getPromotedEvents()){
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

}
