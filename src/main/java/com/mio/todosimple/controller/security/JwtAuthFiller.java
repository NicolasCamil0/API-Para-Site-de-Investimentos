package com.mio.todosimple.controller.security;

import com.mio.todosimple.models.UserTask;
import com.mio.todosimple.models.Usuario;
import com.mio.todosimple.repositories.TaskRepository;
import com.mio.todosimple.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthFiller extends OncePerRequestFilter {

    @Autowired
    private UserTask userTask;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JtwUtil jtwUtil;

    private String obterToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            return authorizationHeader.substring(7);
        }
        return null;

        }

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        String token = obterToken(request);

        if (token != null && jtwUtil.validateToken(token, jtwUtil.extractUsername(token))){
            Long userId = jtwUtil.obterIdUsuario(token);

            Usuario usuario = userRepository.findById(userId).orElse(null);

            if (usuario != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);
            }



        }
    }




}
