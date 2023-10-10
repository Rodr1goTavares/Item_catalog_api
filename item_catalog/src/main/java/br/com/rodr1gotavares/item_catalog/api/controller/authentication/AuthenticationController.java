package br.com.rodr1gotavares.item_catalog.api.controller.authentication;

import br.com.rodr1gotavares.item_catalog.api.dto.auth.LoginDTO;
import br.com.rodr1gotavares.item_catalog.api.dto.auth.RegisterDTO;
import br.com.rodr1gotavares.item_catalog.api.security.JwtTokenService;
import br.com.rodr1gotavares.item_catalog.entity.user.User;
import br.com.rodr1gotavares.item_catalog.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    public AuthenticationController(
            AuthenticationManager authenticationManager, JwtTokenService jwtTokenService,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginData) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
          loginData.username(),
          loginData.password()
        );
        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        User user = (User) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(user);
        Map<String, String> tokenObject = new HashMap<>();
        tokenObject.put("Token: ", token);
        return ResponseEntity.ok(tokenObject);
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
