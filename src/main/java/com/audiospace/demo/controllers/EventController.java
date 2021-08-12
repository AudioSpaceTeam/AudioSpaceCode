package com.audiospace.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.repositories.EventRepository;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Date;

import java.util.List;

@Controller
public class EventController {

  private final EventRepository eventDao;
  private final UserRepository userDao;

  public EventController(EventRepository eventDao, UserRepository userDao) {
    this.eventDao = eventDao;
    this.userDao = userDao;
  }


<<<<<<< HEAD
<<<<<<< HEAD
@GetMapping("/event/create")
  public String createEvent(Model model){
=======
  @GetMapping("/event/create")
  public String createEvent(Model model) {
>>>>>>> a588cb89da32322aa77bbbe232b5d032ec057727
=======

  @GetMapping("/event/create")
  public String createEvent(Model model) {
>>>>>>> 82b77e30983c85b15ef998e6e57bf99a02883222
    model.addAttribute("event", new Event());
    return "event/create";
  }

  //added show an view events
  @GetMapping("/event")
  public String viewEvent(Model model) {
    model.addAttribute("event", eventDao.findAll());
    return "event/index";
  }

  @GetMapping("/event/{id}")
  public String singleEvent(@PathVariable long id, Model model) {
    Event event = eventDao.getById(id);
    model.addAttribute("event", event);
    return "event/show";
  }

  //For create.html

  @PostMapping("/event/create")
  public String saveCreate(@RequestParam(name = "dateTime") String dateTime,
                           @RequestParam(name = "price") String price,
                           @ModelAttribute Event event,
                           Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    event.setPromoter(currentUser);
    event.setPrice(Double.parseDouble(price));

    System.out.println(dateTime);
    System.out.println(LocalDateTime.parse(dateTime));

    event.setStartDateTime(LocalDateTime.parse(dateTime));
    eventDao.save(event);
    model.addAttribute("event", event);
    return "/event/submitted";
  }



}
