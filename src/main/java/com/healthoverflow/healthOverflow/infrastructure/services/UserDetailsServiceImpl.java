package com.healthoverflow.healthOverflow.infrastructure.services;

import com.healthoverflow.healthOverflow.domain.ApplicationUser;
import com.healthoverflow.healthOverflow.infrastructure.ApplicationUserRepo;
import com.healthoverflow.healthOverflow.infrastructure.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser student = applicationUserRepo.findApplicationUserByUsername(username);

        if (student == null) {
            System.out.print("Username not found");
            throw new UsernameNotFoundException((username + " not found"));
        }

//        return student;
        return new org.springframework.security.core.userdetails.User(
                student.getUsername(), student.getPassword(), student.isEnabled(), true, true,
                true, student.getAuthorities());
    }
}
