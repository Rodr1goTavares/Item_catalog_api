package br.com.rodr1gotavares.item_catalog.api.controller.authentication;

import br.com.rodr1gotavares.item_catalog.api.dto.LoginDTO;
import br.com.rodr1gotavares.item_catalog.api.dto.RegisterDTO;
import br.com.rodr1gotavares.item_catalog.entity.user.User;
import br.com.rodr1gotavares.item_catalog.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginData) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                loginData.username(),
                loginData.password()
        );
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerData) {
        if (userRepository.findByUsername(registerData.username()).isPresent()) return ResponseEntity.badRequest().build();
        String encodedPassword = new BCryptPasswordEncoder().encode(registerData.password());
        User newUser = new User(registerData.username(), encodedPassword, registerData.userRole());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
