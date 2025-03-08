package com.mio.todosimple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mio.todosimple.models.Usuario;
import com.mio.todosimple.repositories.CadastroUsuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioService(CadastroUsuario cadastroUsuarioRepository) {
        this.cadastroUsuarioRepository = cadastroUsuarioRepository;
    }

    @Autowired
    private final CadastroUsuario cadastroUsuarioRepository;

    public Usuario salvarUsuario(Usuario usuario){
        return cadastroUsuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return cadastroUsuarioRepository.findAll();
    }


}
