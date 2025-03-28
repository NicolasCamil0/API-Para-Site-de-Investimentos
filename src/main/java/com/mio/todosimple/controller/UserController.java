package com.mio.todosimple.controller;

import com.mio.todosimple.models.Usuario;
import com.mio.todosimple.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Usuario obj = this.userService.findById(id);
        return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    @Validated(Usuario.CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Usuario obj){
        this.userService.create(obj);
        System.out.println("Tentando criar usuário: " + obj.getUsername());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{id}/")
    @Validated(Usuario.UpdateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario obj, @PathVariable Long id){
        obj.setId(id);
        this.userService.update(obj);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
