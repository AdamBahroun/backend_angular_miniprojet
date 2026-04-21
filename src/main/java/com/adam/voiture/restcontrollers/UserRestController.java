package com.adam.voiture.restcontrollers;

import com.adam.voiture.entities.AppUser;
import com.adam.voiture.register.RegistrationRequest;
import com.adam.voiture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserRestController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public AppUser register(@RequestBody RegistrationRequest request) {
        return userService.registerUser(request);
    }

    @GetMapping("/verifyEmail/{code}")
    public AppUser verifyEmail(@PathVariable("code") String code) {
        return userService.validateToken(code);
    }
}