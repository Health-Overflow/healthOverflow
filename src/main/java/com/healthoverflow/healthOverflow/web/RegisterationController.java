package com.healthoverflow.healthOverflow.web;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.healthoverflow.healthOverflow.domain.ApplicationUser;
import com.healthoverflow.healthOverflow.domain.Role;
import com.healthoverflow.healthOverflow.infrastructure.ApplicationUserRepo;
import com.healthoverflow.healthOverflow.infrastructure.RoleRepository;
import com.healthoverflow.healthOverflow.infrastructure.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Controller
public class RegisterationController {

    @Value("${cloud.name}")
    String cloud_name;
    @Value("${api.key}")
    String api_key;
    @Value("${api.secret}")
    String api_secret;

    @Autowired
    private ApplicationUserRepo applicationUserRepo ;
    @Autowired
    private BCryptPasswordEncoder encoder ;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/signup")
    public String getSignupPage(){
//        Role userRole = new Role();
//        userRole.setName("USER");
//        Role adminRole = new Role();
//        adminRole.setName("ADMIN");
//        System.out.println(roleRepository.save(userRole).getName());
//        roleRepository.save(userRole);
//        roleRepository.save(adminRole);
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView createAppUser(@RequestParam String username , @RequestParam String password
            , @RequestParam String fullName , @RequestParam MultipartFile image
            , @RequestParam String bio , @RequestParam String dateOfBirth) throws IOException, ParseException {

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date=formatter2.parse(dateOfBirth);

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloud_name,
                "api_key", api_key,
                "api_secret", api_secret,
                "secure", true));

        Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());

        ApplicationUser applicationUser = new ApplicationUser(uploadResult.get("secure_url").toString(),
                encoder.encode(password),
                username ,fullName,bio,date);
        applicationUser.setRole(securityService.findRoleByName("USER"));

        applicationUser = applicationUserRepo.save(applicationUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(applicationUser , null , new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login" ;
    }
}

