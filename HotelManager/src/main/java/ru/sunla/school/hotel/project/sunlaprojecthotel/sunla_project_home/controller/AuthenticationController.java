package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.dto.AuthenticationRequestDTO;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.UserEntity;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.exeption.InvalidPasswordExeption;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.security.JwtTokenProvider;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private IUserService userService;
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IUserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) throws InvalidPasswordExeption {
        try {
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            UserEntity user = userService.findByEmail(request.getEmail());
            passwordCheck(user.getPassword(), request);
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    private void passwordCheck(String password, AuthenticationRequestDTO request) throws InvalidPasswordExeption {
        System.out.println("c");
        if (!passwordEncoder.matches(request.getPassword(), password)){
            throw new InvalidPasswordExeption();
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}