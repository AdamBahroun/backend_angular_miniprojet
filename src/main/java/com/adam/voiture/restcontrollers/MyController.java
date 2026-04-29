package com.adam.voiture.restcontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Salut, tu es bien authentifié via GitHub !");
    }

    @GetMapping("/me")
    public ResponseEntity<String> me(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Non authentifié");
        }
        return ResponseEntity.ok(String.format(
                "Login: %s | Nom: %s | Email: %s | Avatar: %s",
                principal.getAttribute("login"),
                principal.getAttribute("name"),
                principal.getAttribute("email"),
                principal.getAttribute("avatar_url")));
    }
}