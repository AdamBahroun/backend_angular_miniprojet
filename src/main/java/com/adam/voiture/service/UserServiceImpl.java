package com.adam.voiture.service;

import com.adam.voiture.entities.AppRole;
import com.adam.voiture.entities.AppUser;
import com.adam.voiture.entities.VerificationToken;
import com.adam.voiture.exception.EmailAlreadyExistsException;
import com.adam.voiture.exception.ExpiredTokenException;
import com.adam.voiture.exception.InvalidTokenException;
import com.adam.voiture.register.RegistrationRequest;
import com.adam.voiture.repository.RoleRepository;
import com.adam.voiture.repository.UserRepository;
import com.adam.voiture.repository.VerificationTokenRepository;
import com.adam.voiture.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    VerificationTokenRepository verificationTokenRepo;
    @Autowired
    EmailSender emailSender;

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AppRole addRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public AppUser addRoleToUser(String username, String rolename) {
        AppUser user = userRepository.findByUsername(username);
        AppRole role = roleRepository.findByRole(rolename);
        user.getRoles().add(role);
        return user;
    }

    @Override
    public List<AppUser> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser registerUser(RegistrationRequest request) {
        Optional<AppUser> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent())
            throw new EmailAlreadyExistsException("Email deja utilise !");

        AppUser newUser = new AppUser();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        newUser.setEnabled(false);
        userRepository.save(newUser);

        AppRole role = roleRepository.findByRole("USER");
        List<AppRole> roles = new ArrayList<>();
        roles.add(role);
        newUser.setRoles(roles);
        userRepository.save(newUser);

        String code = generateCode();
        VerificationToken token = new VerificationToken(code, newUser);
        verificationTokenRepo.save(token);
        sendEmailUser(newUser, code);

        return newUser;
    }

    @Override
    public AppUser validateToken(String code) {
        VerificationToken token = verificationTokenRepo.findByToken(code);
        if (token == null)
            throw new InvalidTokenException("Code invalide !");

        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            verificationTokenRepo.delete(token);
            throw new ExpiredTokenException("Code expire !");
        }

        AppUser user = token.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return user;
    }

    private String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private void sendEmailUser(AppUser user, String code) {
        String body = "Bonjour <h1>" + user.getUsername() + "</h1> Votre code de validation est <h1>" + code + "</h1>";
        emailSender.sendEmail(user.getEmail(), body);
    }
}