package com.mio.todosimple.services;

import com.mio.todosimple.models.Usuario;
import com.mio.todosimple.repositories.TaskRepository;
import com.mio.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario findById(Long id){
        Optional<Usuario> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado, id: " + id + "não existe " + Usuario.class.getName()));

    }

    public Usuario findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username: " + username + " não encontrado"));
    }

    @Transactional
    public Usuario create(Usuario obj){
        obj.setId(null);
        obj = (Usuario) this.userRepository.save(obj);
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public Usuario update(Usuario obj){
        Usuario newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return (Usuario) this.userRepository.save(newObj);
    }

    public void delete (Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Não há entidades relacionadas");
        }

    }
        private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }






}
