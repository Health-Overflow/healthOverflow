package com.healthoverflow.healthOverflow.web;


import com.healthoverflow.healthOverflow.infrastructure.SectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SectionController {
    @Autowired
    private SectionRepo sectionRepo;

    @GetMapping("/section/{sectionId}")
    public  String sectionName(@PathVariable Long sectionId , Model model){
        model.addAttribute("title",sectionRepo.findById(sectionId));
return "sections";
    }



}
