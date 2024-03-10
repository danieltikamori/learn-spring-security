package cc.tkmr.learnspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringSecurityApplication.class, args);
	}

}

//Using Spring Security
//
//At first run without any change, it will generate a password displayed at console for the "user" user.
//
//One alternative to use a fixed password for demonstration purposes, put parameters to "application.properties":
//
//```
//spring.security.user.name=user
//spring.security.user.password=user123
//spring.security.user.roles=USERS
//```
//
//Not recommended for production environments.

// In memory user creation:

// Create a class that extends WebSecurityConfigurerAdapter (WeSecurityConfig)
// Add @EnableWebSecurity
// Add @Configuration
// Add @EnableWebMvc // If you use Spring MVC
// Add @EnableGlobalMethodSecurity(prePostEnabled = true) // Deprecated
// Add @EnableGlobalAuthentication // THIS is the new one

// .withDefaultPasswordEncoder() // USE ONLY FOR TESTING!!!! NOT SAFE for production
// Use external hashing instead.








