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

// Add @EnableWebSecurity
// Add @Configuration
// Add @EnableWebMvc // If you use Spring MVC
// Add @EnableGlobalMethodSecurity(prePostEnabled = true) // Deprecated
// Add @EnableGlobalAuthentication // THIS is the new one, depending on situation, it may be useless

// .withDefaultPasswordEncoder() // USE ONLY FOR TESTING!!!! NOT SAFE for production
// Use external hashing instead.

// Create config package
// Inside security package, create WebSecurityConfig.java
// Here we store configuration files

// Create controller package
// Inside controller package, create LoginController.java
// Here we configure login, /login page

// Inside controller package, create WelcomeController.java
// Here we configure welcome, / and other pages

// Auth Database

// Add Spring data jpa starter at pom.xml

// Create model package
// Inside model package, create User.java

// Create repository package
// Inside repository package, create UserRepository.java

// At controller package
// Inside controller package, create UserController.java

// At config package:
// Inside config package, create SecurityDatabaseService.java

// Create init package
// Inside init package, create StartApplication.java

// Include  H2 database in pom.xml

// Test using Postman for login
// GET requests, Authorization tab, select Basic authentication and then username: user and password: user123
// Test /users and /managers









