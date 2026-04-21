package com.adam.voiture;

import com.adam.voiture.entities.AppRole;
import com.adam.voiture.entities.AppUser;
import com.adam.voiture.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import com.adam.voiture.entities.Marque;
import com.adam.voiture.entities.Voiture;

@SpringBootApplication
public class VoitureApplication {

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(VoitureApplication.class, args);
	}

	/* @PostConstruct
	void init() {
		repositoryRestConfiguration.exposeIdsFor(Voiture.class, Marque.class);
	
		userService.addRole(new AppRole(null, "ADMIN"));
		userService.addRole(new AppRole(null, "USER"));
	
		userService.saveUser(new AppUser(null, "Admin", "123", null, true, null));
		userService.saveUser(new AppUser(null, "ahmed", "123", null, true, null));
	
		userService.addRoleToUser("Admin", "ADMIN");
		userService.addRoleToUser("Admin", "USER");
		userService.addRoleToUser("ahmed", "USER");
	} */
}