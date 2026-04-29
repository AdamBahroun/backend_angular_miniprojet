package com.adam.voiture.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    AuthenticationManager authMgr;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // OAuth2 a besoin de sessions : on passe à IF_REQUIRED
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Collections.singletonList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .authorizeHttpRequests(requests -> requests
                        // Routes publiques
                        .requestMatchers("/login", "/register/**", "/verifyEmail/**", "/error").permitAll()
                        // Route de test OAuth2 (atelier) — accessible à tout utilisateur authentifié
                        .requestMatchers(HttpMethod.GET, "/hello").authenticated()
                        .requestMatchers(HttpMethod.GET, "/me").authenticated()
                        // Routes existantes protégées par rôles JWT
                        .requestMatchers(HttpMethod.GET, "/marque/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/marque/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/marque/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/marque/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/rest/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/rest/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/rest/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/rest/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                // Filtres JWT existants
                .addFilterBefore(new JWTAuthenticationFilter(authMgr), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                // OAuth2 GitHub
                .oauth2Login(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));

        return http.build();
    }
}