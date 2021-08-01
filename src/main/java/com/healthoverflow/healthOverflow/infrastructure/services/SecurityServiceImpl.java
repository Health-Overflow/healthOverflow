package com.healthoverflow.healthOverflow.infrastructure.services;


import com.healthoverflow.healthOverflow.domain.Role;
import com.healthoverflow.healthOverflow.infrastructure.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow();
    }
}
