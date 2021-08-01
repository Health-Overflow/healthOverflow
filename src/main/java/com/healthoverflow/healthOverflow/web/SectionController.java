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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SectionController {
    @Autowired
    private SectionRepo sectionRepo;
    @Autowired
    private ApplicationUserRepo applicationUserRepo;
    @Autowired
    private PostRepo postRepo;

    @GetMapping("/section/{sectionId}")
    public  String sectionName(@PathVariable Long sectionId , Model model){
        Section section = sectionRepo.findById(sectionId).orElseThrow();

        System.out.println("......."+section);
        model.addAttribute("section",section);
        model.addAttribute("posts",postRepo.findPostsBySectionId(sectionId));
return "sections";
    }
@PostMapping("/section/{sectionId}")
    public RedirectView postQusetion(@PathVariable Long sectionId,String body){
    System.out.println("mmmmmmmmmmmmm"+sectionId);
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    ApplicationUser currentUser = applicationUserRepo.findApplicationUserByUsername(userDetails.getUsername());
    Post newPost = new Post(currentUser,body,sectionRepo.findById(sectionId).orElseThrow());
    postRepo.save(newPost);
        return new RedirectView("/section/{sectionId}");
}


}
