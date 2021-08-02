package com.healthoverflow.healthOverflow.web;

import com.healthoverflow.healthOverflow.domain.ApplicationUser;
import com.healthoverflow.healthOverflow.domain.Comment;
import com.healthoverflow.healthOverflow.domain.Post;
import com.healthoverflow.healthOverflow.infrastructure.ApplicationUserRepo;
import com.healthoverflow.healthOverflow.infrastructure.CommentRepsitory;
import com.healthoverflow.healthOverflow.infrastructure.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CommentController {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ApplicationUserRepo applicationUserRepo;
    @Autowired
    private CommentRepsitory commentRepsitory;
    @GetMapping("/post/{id}")
    public String postPage(@PathVariable Long id , Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser currentUser = applicationUserRepo.findApplicationUserByUsername(userDetails.getUsername());
        Post post = postRepo.findById(id).orElseThrow();
        Boolean userEqualsIdPost = post.getApplicationUser().getId() == currentUser.getId();
        model.addAttribute("post",post);
        model.addAttribute("userEqualsIdPost",userEqualsIdPost);
        return "PostPage";
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN','DOCTOR')")
    @PostMapping("/post/{id}")
    public RedirectView addComment(@PathVariable Long id, @RequestParam String commentBody){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser currentUser = applicationUserRepo.findApplicationUserByUsername(userDetails.getUsername());
        Post post =postRepo.findById(id).orElseThrow();
        Comment comment = new Comment(commentBody,post,currentUser);
        commentRepsitory.save(comment);
        return new RedirectView("/post/{id}");
    }
}
