package com.audiospace.demo.controllers;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserRepository userDao;
    public UserController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, @RequestParam String isPromoter){
        if(isPromoter.equals("true")){
            user.setPromoter(true);
        } else {
            user.setPromoter(false);
        }
        userDao.save(user);
        return "redirect:/register";
    }
}
