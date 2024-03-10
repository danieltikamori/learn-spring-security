package cc.tkmr.learnspringsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        try {
            http
                    // Require authentication for all requests
                    .authorizeHttpRequests((authorize) -> authorize
//                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/users").hasAuthority("USERS")
                            .requestMatchers("/managers").hasAuthority("MANAGERS")
                            .anyRequest().authenticated()
                    )
                    // Use HTTP Basic authentication
//                    .httpBasic(Customizer.withDefaults())
                    .formLogin(Customizer.withDefaults());

            return http.build();
        } catch (Exception e) {
            throw new RuntimeException("Error building security filter chain", e);
        }
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password("{noop}password") // {noop} cryptography strategy, means no encoding. Use ONLY for demo / test purposes!
                .authorities("USERS")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}password")
                .authorities("MANAGERS", "USERS")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

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
