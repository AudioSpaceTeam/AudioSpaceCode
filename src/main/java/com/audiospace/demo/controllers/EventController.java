package com.audiospace.demo.controllers;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.models.Genre;
import com.audiospace.demo.repositories.EventRepository;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.GenreRepository;
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
  private final GenreRepository genreDao;

  public EventController(EventRepository eventDao, UserRepository userDao, GenreRepository genreDao) {
    this.eventDao = eventDao;
    this.userDao = userDao;
    this.genreDao = genreDao;
  }

  @GetMapping("/event/create")
  public String createEvent(Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!currentUser.getPromoter()) {
      return "redirect:/event";
    }
    model.addAttribute("event", new Event());
    //      List for users who are not promoters
//    List<User> notPromoters = new ArrayList<>();
//    for (User userP : userDao.findAll()) {
////        If a user is not a promoter, then we want to add them to the list.
//      if (!userP.getPromoter()) {
//        notPromoters.add(userP);
//      }
//    }
//    model.addAttribute("users", notPromoters);
    model.addAttribute("genres", genreDao.findAll());
    return "event/create";
  }


  //added show an view events
  @GetMapping("/event")
  public String viewEvent(Model model) {
    model.addAttribute("events", eventDao.findAll());
    model.addAttribute("genres", genreDao.findAll());
    return "event/index";
  }

  @GetMapping("/event/{id}")
  public String singleEvent(@PathVariable long id, Model model) {
    Event event = eventDao.getById(id);
    model.addAttribute("event", event);
    User aUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User currentUser = userDao.findById(aUser.getId());
//    Our boolean to see if the current user is the owner or not.
    boolean isOwner = event.getPromoter().getId() == currentUser.getId();
    //We are setting these false and changing it to true if they are one.
    boolean isPerformer = false;
    boolean isRequester = false;
    boolean isSlotted = false;
//    We are checking if the current user is a performer or not.
    if (!currentUser.getPromoter()) {
      isPerformer = true;
//      We are checking if the current user has the event in their list of request.
      if (currentUser.getRequested().isEmpty()) {
        boolean isReqOrSlot = isRequester || isSlotted;
        model.addAttribute("isReqOrSlot", isReqOrSlot);
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("isPerformer", isPerformer);
        model.addAttribute("isRequester", isRequester);
        model.addAttribute("isSlotted", isSlotted);
        model.addAttribute("currentUser", userDao.findById(currentUser.getId()));
        return "event/show";
      }
      for (Event eventCheck : currentUser.getRequested()) {
        if (eventCheck.getId() == event.getId()) {
          isRequester = true;
          break;
        }
      }
      if (currentUser.getSlotted().isEmpty()) {
        boolean isReqOrSlot = isRequester || isSlotted;
        model.addAttribute("isReqOrSlot", isReqOrSlot);
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("isPerformer", isPerformer);
        model.addAttribute("isRequester", isRequester);
        model.addAttribute("isSlotted", isSlotted);
        model.addAttribute("currentUser", userDao.findById(currentUser.getId()));
        return "event/show";
      }
      for (Event eventCheck : currentUser.getSlotted()) {
        if (eventCheck.getId() == event.getId()) {
          isSlotted = true;
          break;
        }
      }
    }
    boolean isReqOrSlot = isRequester || isSlotted;
    model.addAttribute("isReqOrSlot", isReqOrSlot);
    model.addAttribute("isOwner", isOwner);
    model.addAttribute("isPerformer", isPerformer);
    model.addAttribute("isRequester", isRequester);
    model.addAttribute("isSlotted", isSlotted);
    model.addAttribute("currentUser", userDao.findById(currentUser.getId()));
    return "event/show";
  }


  //For create.html
  @PostMapping("/event/create")
  public String saveCreate(@RequestParam(name = "dateTime") String dateTime,
                           @RequestParam(name = "price") String price,
                           @ModelAttribute Event event,
                           // @RequestParam String[] bandIds,
                           @RequestParam String[] genreIds,
                           Model model) {

    //Added user
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    event.setPromoter(userDao.findById(currentUser.getId()));
    event.setPrice(Double.parseDouble(price));

    event.setStartDateTime(LocalDateTime.parse(dateTime));

//    Below stuff is for making a list of the selected performers.
    List<User> slottedPerformers = new ArrayList<>();


//    for (String band : bandIds) {
//      if (band.equalsIgnoreCase("ignore")) {
//        continue;
//      }
////      System.out.println(band + " Band id");
////      We are ADDING to the slotted performers list,
////      We are finding the user BY ID
////      We are PARSING the long from the STRING ARRAY, because checkboxes return string arrays.
//      slottedPerformers.add(userDao.findById(Long.parseLong(band)));
//    }
//    event.setPerformers(slottedPerformers);
    //    Below stuff is for making a list of the selected performers.
    List<Genre> selectedGenres = new ArrayList<>();

    for (String genre : genreIds) {
      if (genre.equalsIgnoreCase("ignore")) {
        continue;
      }
      System.out.println(genre + " Genre id");

//      We are ADDING to the slotted performers list,
//      We are finding the user BY ID
//      We are PARSING the long from the STRING ARRAY, because checkboxes return string arrays.
      selectedGenres.add(genreDao.findGenreByGenreName(genre));
    }
    event.setGenres(selectedGenres);

    eventDao.save(event);
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

//      List<User> notPromoters = new ArrayList<>();
//      for (User userP : userDao.findAll()) {
////        If a user is not a promoter, then we want to add them to the list.
//        if (!userP.getPromoter()) {
//          notPromoters.add(userP);
//        }
//      }
      List<User> currentPerformers = new ArrayList<>();
      for (User userC : userDao.findAllBySlotted(eventDao.findById(id))) {
        currentPerformers.add(userC);
        userC.setSlotted(new ArrayList<>());
      }
      model.addAttribute("performers", currentPerformers);
//      model.addAttribute("users", notPromoters);
      model.addAttribute("event", event);
      model.addAttribute("genres", genreDao.findAll());
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
//      TODO: figure out why it isn't cascading correctly but this fixes it for now...
      event.setPerformers(new ArrayList<>());
      event.setRequesters(new ArrayList<>());
      event.setGenres(new ArrayList<>());
      eventDao.save(event);
      eventDao.delete(event);
      return "redirect:/event";
    }
  }

  @PostMapping("/event/search")
  public String eventSearch(@RequestParam String search,
                            @RequestParam String[] genreIds,
                            Model model) {

    //    Below stuff is for making a list of the selected performers.
    List<Genre> selectedGenres = new ArrayList<>();

    for (String genre : genreIds) {
      if (genre.equalsIgnoreCase("ignore")) {
        continue;
      }
      System.out.println(genre + " Genre id");
//      We are ADDING to the slotted performers list,
//      We are finding the user BY ID
//      We are PARSING the long from the STRING ARRAY, because checkboxes return string arrays.
      selectedGenres.add(genreDao.findGenreByGenreName(genre));
    }

    List<Event> queryEvents = eventDao.findAllByTitleContainingOrDescriptionContaining(search, search);
//    If no genres are selected only search by desc and title.
    if (selectedGenres.isEmpty()) {
      model.addAttribute("events", queryEvents);
      model.addAttribute("genres", genreDao.findAll());
      return "event/index";

    }
    List<Event> queryGenreEvents = new ArrayList<>();

    for (Event event : queryEvents) {
      for (Genre genre : selectedGenres) {
        if (event.getGenres().contains(genre)) {
          queryGenreEvents.add(event);

        }
      }
    }

    model.addAttribute("events", queryGenreEvents);
    model.addAttribute("genres", genreDao.findAll());
    return "event/index";
  }

  @PostMapping("/event/add/requester")
  public String eventAddRequester(@RequestParam long id,
                                  Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Event event = eventDao.findById(id);
    User requester = userDao.findById(currentUser.getId());

    event.getRequesters().add(requester);
    eventDao.save(event);

    return "redirect:/event/" + id;
  }

  @PostMapping("/event/add/performer")
  public String eventAddPerformer(@RequestParam long id,
                                  @RequestParam long requesterId,
                                  Model model) {
//    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Event event = eventDao.findById(id);
    User requester = userDao.findById(requesterId);

    event.getRequesters().remove(requester);
    event.getPerformers().add(requester);
    eventDao.save(event);

    return "redirect:/event/" + id;
  }

  @PostMapping("/event/remove/requester")
  public String eventRemoveRequester(@RequestParam long id,
                                     @RequestParam long requesterId,
                                     Model model) {
//    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Event event = eventDao.findById(id);
    User requester = userDao.findById(requesterId);

    event.getRequesters().remove(requester);
    eventDao.save(event);

    return "redirect:/event/" + id;
  }

  @PostMapping("/event/remove/performer")
  public String eventRemovePerformer(@RequestParam long id,
                                     @RequestParam long performerId,
                                     Model model) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Event event = eventDao.findById(id);
    User performer = userDao.findById(performerId);

    event.getPerformers().remove(performer);
    eventDao.save(event);

    return "redirect:/event/" + id;
  }


}
