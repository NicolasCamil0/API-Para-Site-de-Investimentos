package com.mio.todosimple.repositories;

import com.mio.todosimple.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroUsuario extends JpaRepository<Usuario, Long> {
}