package com.audiospace.demo.controllers;

import com.audiospace.demo.models.Review;
import com.audiospace.demo.repositories.ReviewRepository;
import com.audiospace.demo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewPageController {
    private final ReviewRepository reviewDao;
    private final UserRepository userDao;

    public ReviewPageController(ReviewRepository reviewDao, UserRepository userDao){
        this.reviewDao = reviewDao;
        this.userDao = userDao;
    }

    @GetMapping("reviews/rate")
    public String ratePromoter(Model model){
        model.addAttribute("review", new Review());
        return "reviews/rate";
    }

//    @PostMapping("reviews/rate")
//    public String showRating(){
//        return
//    }

    @GetMapping("reviews/your_rating")
    public String yourRatePromoter(){
        return "reviews/your_rating";
    }

    public void save(Review review) {
    }
}
