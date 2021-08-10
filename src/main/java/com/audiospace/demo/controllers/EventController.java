package com.audiospace.demo.controllers;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.repositories.EventRepository;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {
    private final EventRepository eventDao;
    private final UserRepository userDao;

    public EventController(EventRepository eventDao, UserRepository userDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
    }

    @GetMapping("/event/show")
    public String showEvent(Model model) {
        List<Event> events = eventDao.findAll();
        model.addAttribute("events", events);
        return "showFirstIdea";
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
    public String saveCreate(@ModelAttribute Event event) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        event.setPromoter(userDao.findById(currentUser.getId()));

        eventDao.save(event);
        return "redirect:/event/submitted";
    }

}
