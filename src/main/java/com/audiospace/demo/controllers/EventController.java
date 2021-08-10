package com.audiospace.demo.controllers;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.repositories.EventRepository;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.EventRepository;
import com.audiospace.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class EventController {
  private final EventRepository eventDao;
  private final UserRepository userDao;

  public EventController(EventRepository eventDao, UserRepository userDao) {
    this.eventDao = eventDao;
    this.userDao = userDao;
  }

  @GetMapping("/event/create")
  public String createAd(Model model) {
    model.addAttribute("event", new Event());
    return "event/create";
  }


  @GetMapping("/event/submitted")
  public String formSubmitted() {
    return "event/submitted";
  }


  @PostMapping("/event/create")
  public String saveCreate(@RequestParam(name = "dateTime") Date dateTime,
                           @ModelAttribute Event event) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    event.setPromoter(userDao.findById(currentUser.getId()));
    System.out.println(dateTime);
//    event.setDateTime(Date.parse(dateTime));


    eventDao.save(event);
    return "redirect:/event/submitted";
  }

}
