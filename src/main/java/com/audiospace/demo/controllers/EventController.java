package com.audiospace.demo.controllers;

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

  public EventController(EventRepository eventDao, UserRepository userDao){
    this.eventDao = eventDao;
    this.userDao = userDao;
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

    @GetMapping("/event/create")
    public String createEvent(Model model) {
        model.addAttribute("event", new Event());
        return "/event/create";
    }


    @PostMapping("/event/create")
    public String saveCreate(@RequestParam(name = "startDateTime") String startDateTime, @RequestParam(name = "endDateTime") String endDateTime, @ModelAttribute Event event, Model model) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        event.setPromoter(currentUser);
        event.setStartDateTime(LocalDateTime.parse(startDateTime));
        event.setEndDateTime(LocalDateTime.parse(endDateTime));
        eventDao.save(event);
        model.addAttribute("event", event);
        return "/event/submitted";
    }


}
