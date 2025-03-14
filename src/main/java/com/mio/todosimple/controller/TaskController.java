package com.mio.todosimple.controller;

import com.mio.todosimple.models.UserTask;
import com.mio.todosimple.models.Usuario;
import com.mio.todosimple.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<UserTask> findById(@PathVariable Long id){
        UserTask obj = this.taskService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity <List<UserTask>> findAllByUserId(@PathVariable Long userId){
        List<UserTask> tasks = this.taskService.findAllByID(userId);
        return ResponseEntity.ok().body(tasks);

    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody UserTask obj){
        this.taskService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    @Validated
    public ResponseEntity<Void> update (@Valid @RequestBody UserTask obj, @PathVariable Long id) {
        obj.setId(id);
        this.taskService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
