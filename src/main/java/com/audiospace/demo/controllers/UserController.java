package com.audiospace.demo.controllers;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.EventRepository;
import com.audiospace.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
  private final UserRepository userDao;
  //  added login properties & passwordEncoder dependency
  private final PasswordEncoder passwordEncoder;
  private final EventRepository eventDao;

  public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, EventRepository eventDao) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
    this.eventDao = eventDao;
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
  public String showUserInfo(Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    long id = currentUser.getId();

    List<Event> eventsList = eventDao.findAll();
    List<Event> userEvents = new ArrayList<>();
    for(Event event : eventsList){
      if(event.getPromoter().getId() == currentUser.getId()){
        userEvents.add(event);
      }
    }
    model.addAttribute("userEvents", userEvents);


//    List<Event> events = currentUser.getPromotedEvents();
//    model.addAttribute("testEvent", testEvent);
//    model.addAttribute("userEvents", currentUser.getPromotedEvents());
    model.addAttribute("user", currentUser);
    return "profile";
  }
}
