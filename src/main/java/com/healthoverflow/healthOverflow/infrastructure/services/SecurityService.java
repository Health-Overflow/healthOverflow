package com.healthoverflow.healthOverflow.infrastructure.services;

import com.healthoverflow.healthOverflow.domain.Role;

public interface SecurityService {
    Role findRoleById(Long roleId);
    Role findRoleByName(String name);
}
