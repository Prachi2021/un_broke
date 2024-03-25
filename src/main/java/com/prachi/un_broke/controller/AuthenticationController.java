package com.prachi.un_broke.controller;

import com.prachi.un_broke.dto.UserLoginDTO;
import com.prachi.un_broke.model.User;
import com.prachi.un_broke.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthenticationController(UserService userService, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User user1 = userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest req, @RequestBody UserLoginDTO uDTO) {
        // Perform authentication logic
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(auth.getName());
        // Return appropriate response or token
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Perform logout logic
        // Invalidate token or session
        return ResponseEntity.ok("Logout successful");
    }

}
