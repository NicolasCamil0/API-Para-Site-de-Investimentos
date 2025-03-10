package com.mio.todosimple.repositories;

import com.mio.todosimple.models.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<UserTask, Long> {

    List<UserTask> findByUsuario_Id(Long id);

    //@Query(value = "SELECT t FROM Task WHERE t.usuario.id = :id")
    //List<Task> findByUsuario_Id (@Param("id") Long id);

    //@Query(value = "SELECT * FROM task t WHERE t.usuario.id = usuario.id = :id", nativeQuery = true)
    //List<Task> findByIdUsuario_Id(@Param("id") Long id);



}
