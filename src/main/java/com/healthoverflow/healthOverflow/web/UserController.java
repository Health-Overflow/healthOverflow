package com.healthoverflow.healthOverflow.web;

import com.healthoverflow.healthOverflow.domain.ApplicationUser;
import com.healthoverflow.healthOverflow.domain.Post;
import com.healthoverflow.healthOverflow.domain.Section;
import com.healthoverflow.healthOverflow.infrastructure.ApplicationUserRepo;
import com.healthoverflow.healthOverflow.infrastructure.PostRepo;
import com.healthoverflow.healthOverflow.infrastructure.SectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    @Autowired
    private ApplicationUserRepo applicationUserRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private SectionRepo sectionRepo;

    @GetMapping("/profile")
    public String getProfile(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser currentUser = applicationUserRepo.findApplicationUserByUsername(userDetails.getUsername());

        model.addAttribute("user" , currentUser);
        model.addAttribute("posts" , postRepo.findPostsByApplicationUserId(currentUser.getId()));
       model.addAttribute("sections", sectionRepo.findAll());
        return "Profile";



    }

    @PostMapping("/profile")
    public RedirectView profileAddPost(@RequestParam String body , @RequestParam String sectionName){
       Section section= sectionRepo.findSectionByTitle(sectionName);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser currentUser = applicationUserRepo.findApplicationUserByUsername(userDetails.getUsername());
        Post newPost = new Post(currentUser,body,section);
        postRepo.save(newPost);
        return new RedirectView("/profile");

    }
}
