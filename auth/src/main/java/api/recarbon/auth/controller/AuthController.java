package api.recarbon.auth.controller;

import api.recarbon.auth.dto.LoginUserDto;
import api.recarbon.auth.dto.RegisterUserDto;
import api.recarbon.auth.entity.User;
import api.recarbon.auth.payload.LoginResponse;
import api.recarbon.auth.service.AuthService;
import api.recarbon.auth.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @GetMapping("/hello")
    public String callHello() {
        return "HELLOOOO";
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registerUser = authService.signup(registerUserDto);

        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticateUser = authService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticateUser);
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setToken(jwtToken);
        loginResponse.setExpiredIn(jwtService.getExpirationTime());
        loginResponse.setEmail(authenticateUser.getEmail());

        return ResponseEntity.ok(loginResponse);
    }
}