package com.audiospace.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.*;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.repositories.EventRepository;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;

@Controller
public class EventController {

  private final EventRepository eventDao;
  private final UserRepository userDao;

  public EventController(EventRepository eventDao, UserRepository userDao) {
    this.eventDao = eventDao;
    this.userDao = userDao;
  }


  @GetMapping("/event/create")
  public String createEvent(Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(!currentUser.getPromoter()){
      return "redirect:/event";
    }
    model.addAttribute("event", new Event());

    //      List for users who are not promoters
    List<User> notPromoters = new ArrayList<>();
    for(User userP : userDao.findAll()){
//        If a user is not a promoter, then we want to add them to the list.
      if(!userP.getPromoter()){
        notPromoters.add(userP);
      }
    }
    model.addAttribute("users", notPromoters);
    return "event/create";
  }

  //added show an view events
  @GetMapping("/event")
  public String viewEvent(Model model) {
    //      TODO: make it so only users who are NOT promoters show in list.
    model.addAttribute("events", eventDao.findAll());
    return "event/index";
  }

  @GetMapping("/event/{id}")
  public String singleEvent(@PathVariable long id, Model model) {
    Event event = eventDao.getById(id);
    model.addAttribute("event", event);
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    Our boolean to see if the current user is the owner or not.
    model.addAttribute("isOwner", event.getPromoter().getId() == currentUser.getId());
    model.addAttribute("performers", event.getSlottedUsers());
    return "event/show";
  }

  //For create.html

  @PostMapping("/event/create")
  public String saveCreate(@RequestParam(name = "dateTime") String dateTime,
                           @RequestParam(name = "price") String price,
                           @ModelAttribute Event event,
                           @RequestParam String[] bandIds,
                           Model model) {

    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    event.setPromoter(userDao.findById(currentUser.getId()));
    event.setPrice(Double.parseDouble(price));

    event.setStartDateTime(LocalDateTime.parse(dateTime));
    List<User> slottedPerformers = new ArrayList<>();
    for(String band : bandIds){
      if(band.equalsIgnoreCase("ignore")){
        continue;
      }
//      System.out.println(band + " Band id");
//      We are ADDING to the slotted performers list,
//      We are finding the user BY ID
//      We are PARSING the long from the STRING ARRAY, because checkboxes return string arrays.
      slottedPerformers.add(userDao.findById(Long.parseLong(band)));
    }
    event.setSlottedUsers(slottedPerformers);
    eventDao.save(event);
    for(User slotted : slottedPerformers){
      slotted.getSlotted().add(event);
      userDao.save(slotted);
    }
    model.addAttribute("user", currentUser);
    model.addAttribute("event", event);
    return "event/submitted";
  }

  @GetMapping("/event/{id}/edit")
  public String editEvent(@PathVariable long id, Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Event event = eventDao.getById(id);
    if (event.getPromoter().getId() != currentUser.getId()) {
      return "redirect:/event/" + id;
    } else {

//      List for users who are not promoters
      List<User> notPromoters = new ArrayList<>();
      for(User userP : userDao.findAll()){
//        If a user is not a promoter, then we want to add them to the list.
        if(!userP.getPromoter()){
          notPromoters.add(userP);
        }
      }
      model.addAttribute("users", notPromoters);
      model.addAttribute("event", event);
      return "event/edit";
    }
  }

  @PostMapping("/event/{id}/delete")
  public String eventDelete(@RequestParam(name = "id") long id) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Event event = eventDao.getById(id);
    if (event.getPromoter().getId() != currentUser.getId()) {
      return "redirect:/event/" + id;
    } else {
      eventDao.deleteById(id);
      return "redirect:/event";
    }
  }


}
