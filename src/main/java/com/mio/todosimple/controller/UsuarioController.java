package com.mio.todosimple.controller;

import com.mio.todosimple.models.Usuario;
import com.mio.todosimple.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")
    public Usuario criarusuario(@RequestBody Usuario usuario){
        return usuarioService.salvarUsuario(usuario);
    }





}
