package com.mio.todosimple.services;

import com.mio.todosimple.exceptions.IdNotFoundException;
import com.mio.todosimple.models.Usuario;
import com.mio.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Usuario> usuarioOptional = userRepository.findByUsername(username);  // Buscar pelo username

        if (usuarioOptional.isEmpty()){
            throw new IdNotFoundException("Usuário com nome: " + username + " não encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }

    public UserDetails loadUserById (Long Id) throws IdNotFoundException {
        Optional<Usuario> usuarioOptional = userRepository.findById(Id);

        if (usuarioOptional.isEmpty()){
            throw new IdNotFoundException("Usuário com ID:" + Id + "Não encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        return User.builder().username(usuario.getUsername()).
                password(usuario.getPassword()).
                roles("USER").build();
    }

}
