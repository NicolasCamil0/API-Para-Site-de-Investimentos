package com.mio.todosimple.controller;

import com.mio.todosimple.Auth.AuthResponse;
import com.mio.todosimple.Auth.LoginRequest;
import com.mio.todosimple.controller.security.JtwUtil;
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


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JtwUtil jtwUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private LoginRequest loginRequest;


    @PostMapping("/login")
    public ResponseEntity<?> authenticatorUser(@RequestBody LoginRequest loginRequest) {
        //
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));


        String username = loginRequest.getUsername();
        Usuario user = userService.findByUsername(username);


        if (user == null) {
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }


        String token = jtwUtil.generateToken(username);


        return ResponseEntity.ok(new AuthResponse(token));
    }








}
