package be.gestion.naturopathie.dimitri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import be.gestion.naturopathie.dimitri.repository.UserRepository;

/**
 * Classe de configuration de la sécurité Spring Security.
 * Définit les règles d'accès, les stratégies d'authentification et la gestion des sessions.
 */
@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Définition des routes accessibles sans authentification.
     */
    private static final String[] PUBLIC_URLS = {
        "/users/login",
        "/users/signup",
        "/style.css",
        "/avocat.png"
    };

    /**
     * Définition des routes nécessitant une authentification.
     */
    private static final String[] AUTHENTICATED_URLS = {
        "/appointments",
        "/statistical"
    };

    /**
     * Définit les autorisations d'accès, la gestion du formulaire de connexion,
     * le mécanisme CSRF et la configuration de la déconnexion.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )

            .authorizeHttpRequests(authz -> authz
                .requestMatchers(PUBLIC_URLS).permitAll()
                .requestMatchers(AUTHENTICATED_URLS).authenticated()
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/users/login") 
                .defaultSuccessUrl("/appointments", true)
                .failureUrl("/users/login?error=true")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/users/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
            );

        return http.build();
    }

    /**
     * Utilisé pour l'authentification via formulaire.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Permet à Spring Security de charger les utilisateurs depuis la DB.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        
        authProvider.setUserDetailsService(username ->
            userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username))
        );

        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Utilisé pour le hachage sécurisé.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
