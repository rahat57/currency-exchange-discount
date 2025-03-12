package com.currencyexchange.app.auth;

import com.currencyexchange.app.security.JwtTokenProvider;
import com.currencyexchange.app.user.User;
import com.currencyexchange.app.user.UserRequest;
import com.currencyexchange.app.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    /**
     * @author Rahat
     * @since 11-03-2025
     * @apiNote function login and get Token
     */
    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Map<String,Object> result = new HashMap<>();
        result.put("token",jwtTokenProvider.generateToken(username));
        return ResponseEntity.ok().body(result);
        }

    /**
     * @author Rahat
     * @since 11-03-2025
     * @apiNote function add user
     */
    @PostMapping("/register")
    @Operation(summary = "User registration")
    public String register(@Valid @RequestBody UserRequest user) {
        userService.save(user);
        return "User registered successfully";
    }
}
