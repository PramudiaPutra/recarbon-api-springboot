package api.recarbon.auth.service;

import api.recarbon.auth.dto.LoginUserDto;
import api.recarbon.auth.dto.RegisterUserDto;
import api.recarbon.auth.entity.User;
import api.recarbon.auth.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signup(RegisterUserDto userVo) {
        User user = new User();
        user.setEmail(userVo.getEmail());
        user.setPassword(passwordEncoder.encode(userVo.getPassword()));
        user.setUsername(userVo.getUsername());
        user.setFirstName(userVo.getFirstName());
        user.setRole(userVo.getRole());

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto userVo) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userVo.getEmail(),
                        userVo.getPassword()
                )
        );

        return userRepository.findByEmail(userVo.getEmail()).orElseThrow();
    }
}
