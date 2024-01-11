package fr.nicolas.godin.shoot_training_api.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtFilter jwtFilter;

    /**
     * Constucteur
     * @param passwordEncoder config de Bcrypt
     * @param jwtFilter le filtre JWT
     */
    public ApiSecurityConfig(BCryptPasswordEncoder passwordEncoder, JwtFilter jwtFilter) {

        this.passwordEncoder = passwordEncoder;
        this.jwtFilter = jwtFilter;

    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

    /**
     * Configuration de springboot security
     * @param userDetailsService userDetailsService implementer dans AuthenticationService
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider (UserDetailsService userDetailsService) {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);
        return daoAuthenticationProvider;

    }

    // Filtre les route de l'api pour les fermer et obliger a etre authentifier sauf sur les route defini dans le requestMatcher
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("api/registration/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"api/authentication/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"api/shooter/forgot-password").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer ->
                            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(
                        jwtFilter, UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    /**
     * Laisser passer la route de configuration de openApi pour recuperer la doc de l'api
     * @return la route de la doc openApi
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**");

    }
}
