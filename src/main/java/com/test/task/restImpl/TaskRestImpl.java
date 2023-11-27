package com.test.task.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.test.task.POJO.Task;
import com.test.task.constants.TaskConstants;
import com.test.task.rest.TaskRest;
import com.test.task.service.TaskService;
import com.test.task.utils.TaskUtils;

@RestController
public class TaskRestImpl implements TaskRest {

    @Autowired
    TaskService taskService;

    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            return taskService.getAllTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<Task>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addNewTask(Map<String, String> requestMap) {
        try {
            return taskService.addNewTask(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaskUtils.getResponseEntity(TaskConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Task> getTaskById(Long id) {
        try {
            return taskService.getTaskById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Task>(new Task(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> updateTask(Long id, Map<String, String> requestMap) {
        try {
            return taskService.updateTask(id, requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaskUtils.getResponseEntity(TaskConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteTask(Long id) {
        try {
            return taskService.deleteTask(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaskUtils.getResponseEntity(TaskConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
