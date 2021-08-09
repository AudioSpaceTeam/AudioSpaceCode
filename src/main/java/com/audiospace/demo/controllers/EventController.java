package com.audiospace.demo.controllers;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.repositories.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventController {
  private final EventRepository eventDao;

  public EventController(EventRepository eventDao){
    this.eventDao = eventDao;
  }

  @GetMapping("/event/create")
  public String createAd(Model model) {
    model.addAttribute("event",new Event());
    return "event/create";
  }

  @GetMapping("/event/submitted")
  public String formSubmitted(){
    return "event/submitted";
  }

  @PostMapping("/event/create")
  public String saveCreate(@ModelAttribute Event event){
    event.setPrice(5.00);
    event.setSlots(4);
    eventDao.save(event);
    return "redirect:/event/submitted";
  }
}
