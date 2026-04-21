package com.adam.voiture.service;

import com.adam.voiture.entities.AppRole;
import com.adam.voiture.entities.AppUser;
import com.adam.voiture.register.RegistrationRequest;
import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);

    AppUser findUserByUsername(String username);

    AppRole addRole(AppRole role);

    AppUser addRoleToUser(String username, String rolename);

    List<AppUser> findAllUsers();

    AppUser registerUser(RegistrationRequest request);

    AppUser validateToken(String code);
}