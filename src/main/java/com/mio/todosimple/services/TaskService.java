package com.mio.todosimple.services;

import com.mio.todosimple.models.UserTask;
import com.mio.todosimple.models.Usuario;
import com.mio.todosimple.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public UserTask findById(Long Id){
        Optional<UserTask> task = this.taskRepository.findById(Id);
        return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

    }

    @Transactional
    public UserTask create(UserTask obj){
        Usuario usuario = this.userService.findById(obj.getUsuario().getId());
        obj.setId(null);
        obj.setUsuario(usuario);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public UserTask update(UserTask obj){
        UserTask newobj = findById(obj.getId());
        newobj.setDescricao(obj.getDescricao());
        return this.taskRepository.save(newobj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Atpsklfdpósdakmfdopsa,d");
        }
    }
}
