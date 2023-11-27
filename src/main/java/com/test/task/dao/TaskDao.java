package com.test.task.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.test.task.POJO.Task;

public interface TaskDao extends JpaRepository<Task, Long> {

    List<Task> getAllTasks();

    Task getTaskById(@Param("id") Long id);
}
