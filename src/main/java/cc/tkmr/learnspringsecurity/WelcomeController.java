package cc.tkmr.learnspringsecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Welcome to My Spring Boot Web API");
    }
    @GetMapping("/users")
    public ResponseEntity<String> users() {
        return ResponseEntity.ok("Authorized user");
    }
    @GetMapping("/managers")
    public ResponseEntity<String> managers() {
        return ResponseEntity.ok("Authorized manager");
    }
}