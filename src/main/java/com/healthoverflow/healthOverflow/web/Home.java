package com.healthoverflow.healthOverflow.web;

import com.healthoverflow.healthOverflow.domain.Section;
import com.healthoverflow.healthOverflow.infrastructure.SectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Home {
    @Autowired
    private SectionRepo sectionRepo;


    @GetMapping("/")
    public String homePage(Model model){
        List<Section> sections=sectionRepo.findAll();
        model.addAttribute("sections",sections);
        return "index";
    }

}
