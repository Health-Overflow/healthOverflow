package com.healthoverflow.healthOverflow.web;

import com.healthoverflow.healthOverflow.domain.ApplicationUser;
import com.healthoverflow.healthOverflow.infrastructure.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class currentUser {
    @Autowired
    private ApplicationUserRepo applicationUserRepo;

    @GetMapping("/senduser")
    @ResponseBody
    public String getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser currentUser = applicationUserRepo.findApplicationUserByUsername(userDetails.getUsername());
        return "{" +"\"fullName\"" + ":\""+currentUser.getFullName() +"\"," + "\"userImage\"" + ":\"" + currentUser.getImage() +"\"}";
    }
}
