package com.mio.todosimple.controller;

import com.mio.todosimple.Auth.LoginRequest;
import com.mio.todosimple.models.Usuario;
import com.mio.todosimple.services.CustomUserDetailsService;
import com.mio.todosimple.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //@Autowired
    //private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticatorUser (@RequestBody LoginRequest loginRequest){
        Authentication authenticator = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Usuario user = userService.findByUsername(username);

        return new ResponseEntity<>("Usuário autenticado com ID: " + user, HttpStatus.OK);
    }




}
