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


@GetMapping("/event/create")
  public String createEvent(Model model){
    model.addAttribute("event", new Event());
    return "event/create";
}

  @PostMapping("/event/create")
  public String saveCreate(@RequestParam(name = "dateTime") String dateTime,
                           @ModelAttribute Event event,
                           Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    event.setPromoter(currentUser);

    System.out.println(dateTime);
    System.out.println(LocalDateTime.parse(dateTime));

    event.setStartDateTime(LocalDateTime.parse(dateTime));
    eventDao.save(event);
    model.addAttribute("event", event);
    return "event/submitted";
}


}
