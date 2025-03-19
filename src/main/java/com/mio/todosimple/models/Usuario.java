package com.mio.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mio.todosimple.controller.TaskController;
import com.mio.todosimple.repositories.TaskRepository;
import com.mio.todosimple.repositories.UserRepository;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table (name = Usuario.TABLE_NAME)
@Component
public class Usuario {
    public interface CreateUser {}
    public interface UpdateUser {}
    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull (groups = CreateUser.class)
    @NotEmpty (groups = CreateUser.class)
    @Size (groups = CreateUser.class, min = 2, max = 100)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull (groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups =  {CreateUser.class, UpdateUser.class})
    @Size (groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List <UserTask> tasks = new ArrayList<>();


    public List<UserTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<UserTask> tasks) {
        this.tasks = tasks;
    }

    public Usuario(){}

    public Usuario(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Usuario))
            return false;
        if (obj == null || !(obj instanceof Usuario))
            return false;
        Usuario other = (Usuario) obj;
        if (this.id == null)
            if (other.id != null)
                return false;
        else if (!this.id.equals(other.id))
            return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null)? 0 : this.id.hashCode());
        return result;
    }
}
