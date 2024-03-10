package cc.tkmr.learnspringsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private SecurityDatabaseService securityDatabaseService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityDatabaseService).passwordEncoder(NoOpPasswordEncoder.getInstance()); //NoOpPasswordEncoder is deprecated, use for testing
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        try {
            http
                    // Require authentication for all requests
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/users").hasAnyRole("USERS", "MANAGERS") // Use ROLE_. Use case: RBAC
                            .requestMatchers("/managers").hasAnyRole("MANAGERS")
// hasAuthority():
// Checks if the user has a specific authority assigned, regardless of any prefix.
// This can be useful for more granular control or integrating with external authorization systems that don't use the "ROLE_" prefix.
// Use it with caution, as it bypasses Spring Security's role management conventions.

//                            Here's when to use each:
//
//            - Prefer hasRole for most RBAC scenarios within your Spring Security application.
//            - Use hasAuthority only when:
//                    You need to check for a specific permission without the "ROLE_" prefix.
//                    Integrating with external authorization systems that don't use the Spring Security role convention.
//            Additional Considerations:
//                    Remember to assign roles or authorities to your users using UserDetailsService.
//                    Consider using annotations like @PreAuthorize for method-level authorization checks.

                            .anyRequest().authenticated()
                    )
                    // Use HTTP Basic authentication
                    .httpBasic(Customizer.withDefaults());

            return http.build();
        } catch (Exception e) {
            throw new RuntimeException("Error building security filter chain", e);
        }
    }

// For in-memory use
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User
//                .withUsername("user")
//                .password("{noop}password") // {noop} cryptography strategy, means no encoding. Use ONLY for demo / test purposes!
//                .authorities("USERS")
//                .build();
//        UserDetails admin = User
//                .withUsername("admin")
//                .password("{noop}password")
//                .authorities("MANAGERS", "USERS")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
