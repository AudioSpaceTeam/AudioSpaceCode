package com.audiospace.demo.controllers;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.repositories.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventController {
    private final EventRepository eventDao;

    public EventController(EventRepository eventDao){
        this.eventDao = eventDao;
    }

    @GetMapping("/event/create")
    public String createAd(Model model) {
        model.addAttribute("event",eventDao.findAll());
        return "event/create";
    }

    @GetMapping("/event/submitted")
    public String formSubmitted(){
        return "event/submitted";
    }
//    @PostMapping("/event/create")
//    public String saveCreate(@RequestParam(name = "title") String title,
//        @RequestParam(name="location")String location,
//        @RequestParam(name="description")String description){
//            EventModel event = new EventModel();
//            event.setTitle(title);
//            event.setLocation(location);
//            event.setDescripion(description);
//            return "redirect:/event/submitted";
//    }
}
