package com.test.task.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.test.task.POJO.Task;

public interface TaskService {
    ResponseEntity<List<Task>> getAllTasks();

    ResponseEntity<String> addNewTask(Map<String, String> requestMap);

    ResponseEntity<Task> getTaskById(Long id);

    ResponseEntity<String> updateTask(Long id, Map<String, String> requestMap);

    ResponseEntity<String> deleteTask(Long id);
}
