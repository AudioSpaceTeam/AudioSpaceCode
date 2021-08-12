package com.audiospace.demo.controllers;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MapController {
    private final EventRepository eventDao;
    public MapController(EventRepository eventDao){
        this.eventDao = eventDao;

    }

    @GetMapping("/map")
    public String showSignupForm(Model model) {
        List<Event> eventsList = eventDao.findAll();
        model.addAttribute("eventList", eventsList);
        return "map";
    }
}
