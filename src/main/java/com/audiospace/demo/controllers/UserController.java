package com.audiospace.demo.controllers;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.EventRepository;
import com.audiospace.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;
    //  added login properties & passwordEncoder dependency
    private final PasswordEncoder passwordEncoder;
    private final EventRepository eventDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, EventRepository eventDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.eventDao = eventDao;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, @RequestParam String isPromoter) {
        if (isPromoter.equals("true")) {
            user.setPromoter(true);
        } else {
            user.setPromoter(false);
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showUserInfo(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userEvents", userDao.findById(currentUser.getId()).getPromotedEvents());
        model.addAttribute("user", userDao.findById(currentUser.getId()));

        model.addAttribute("profileOwner", true);

        return "profile";
    }


    @GetMapping("/profile/{id}")
    public String showUserInfo(@PathVariable long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userEvents", userDao.findById(id).getPromotedEvents());
        model.addAttribute("user", userDao.findById(id));
        model.addAttribute("profileOwner", id == currentUser.getId());

        return "profile";
    }

    @GetMapping("/profile/{id}/edit")
    public String editUserInfo(@PathVariable long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.getId() != userDao.findById(id).getId()) {
            return "redirect:/profile/" + id;
        } else {
            model.addAttribute("user", userDao.findById(id));
            return "user/edit";
        }


  }

  @PostMapping("/profile/{id}/edit")
  public String saveUserInfo(@ModelAttribute User user,
                             @PathVariable long id,
                             Model model,
                             @RequestParam String isPromoter,
                             @RequestParam String password) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (currentUser.getId() != userDao.findById(id).getId()) {
      return "redirect:/profile/" + id;
    } else {
      if (isPromoter.equals("true")) {
        user.setPromoter(true);
      } else {
        user.setPromoter(false);
      }
      if (!passwordEncoder.matches(password, currentUser.getPassword())) {
//        System.out.println(currentUser.getPassword());
//        System.out.println(password);
//        System.out.println(passwordEncoder.matches(currentUser.getPassword(), password));
        return "redirect:/profile/" + id + "/edit";
      }
      String hash = passwordEncoder.encode(password);
      user.setPassword(hash);
      userDao.save(user);
      model.addAttribute("user", userDao.findById(id));
      return "profile";

    }

    @PostMapping("/profile/{id}/edit")
    public String saveUserInfo(@ModelAttribute User user,
                               @PathVariable long id,
                               Model model,
                               @RequestParam String isPromoter,
                               @RequestParam String password) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.getId() != userDao.findById(id).getId()) {
            return "redirect:/profile/" + id;
        } else {
            if (isPromoter.equals("true")) {
                user.setPromoter(true);
            } else {
                user.setPromoter(false);
            }
            if (!passwordEncoder.matches(password, currentUser.getPassword())) {
                System.out.println(currentUser.getPassword());
                System.out.println(password);
                System.out.println(passwordEncoder.matches(currentUser.getPassword(), password));
                return "redirect:/profile/" + id + "/edit";
            }
            String hash = passwordEncoder.encode(password);
            user.setPassword(hash);
            userDao.save(user);
            model.addAttribute("user", userDao.findById(id));
            return "profile";
        }


    }

    //User delete account option. Im no sure.

    @PostMapping("/profile/{id}/delete")
    public String deleteUser(@PathVariable long id) {
        System.out.println("id = " + id);
        System.out.println("userDao.getById(id).getUsername() = " + userDao.getById(id).getUsername());
        User user = userDao.getById(id);
        user.setPassword(null);
        user.setPromoter(null);
        user.setUsername(null);
        userDao.delete(userDao.getById(id));
        return "redirect:/user";
    }

  @PostMapping("/profile/{id}/delete")
  public String deleteUser(@ModelAttribute User user,
                           @PathVariable long id,
                           @RequestParam String passwordDelete) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (currentUser.getId() != userDao.findById(id).getId()) {
      return "redirect:/profile/" + id;
    } else {
      if (!passwordEncoder.matches(passwordDelete, currentUser.getPassword())) {
        return "redirect:/profile/" + id + "/edit";
      }
      User deleteMe = userDao.findById(currentUser.getId());
      userDao.deleteById(deleteMe.getId());
      return "redirect:/login";
//      Todo: Ask about where to redirect for logout after deleting a user...
    }

  }


//  Come back to this above, to make it check if the user owns the profile or not.
//  If they own it, then IT should display a different welcome message...
//  and enable an edit button?
//  and if not a review button.

}
