package com.test.task.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.task.POJO.Task;

@RequestMapping(path = "/tasks")
public interface TaskRest {

    @GetMapping(path = "")
    ResponseEntity<List<Task>> getAllTasks();

    @PostMapping(path = "")
    ResponseEntity<String> addNewTask(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id);

    @PutMapping(path = "/{id}")
    ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Map<String, String> requestMap);

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteTask(@PathVariable Long id);
}