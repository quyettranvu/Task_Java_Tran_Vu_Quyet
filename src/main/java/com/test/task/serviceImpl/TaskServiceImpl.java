package com.test.task.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.task.POJO.Task;
import com.test.task.constants.TaskConstants;
import com.test.task.dao.TaskDao;
import com.test.task.service.TaskService;
import com.test.task.utils.TaskUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDao taskDao;

    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            return new ResponseEntity<List<Task>>(taskDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<Task>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addNewTask(Map<String, String> requestMap) {
        try {
            if (validateTaskMap(requestMap)) {
                taskDao.save(getTaskFromMap(null, requestMap, false));
                return TaskUtils.getResponseEntity("Task added successfully!", HttpStatus.OK);
            }
            return TaskUtils.getResponseEntity(TaskConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(TaskConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Task> getTaskById(Long id) {
        try {
            Task task = taskDao.getTaskById(id);
            if (task == null) {
                log.info("Task not found! You will receive a null task");
                return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Task(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateTask(Long id, Map<String, String> requestMap) {
        try {
            if (validateTaskMap(requestMap)) {
                Optional<Task> optional = taskDao.findById(id);
                if (!optional.isEmpty()) {
                    taskDao.save(getTaskFromMap(id, requestMap, true));
                    return TaskUtils.getResponseEntity("Task updated successfully", HttpStatus.OK);
                } else {
                    return TaskUtils.getResponseEntity("Task id does not exist", HttpStatus.OK);
                }
            }
            return TaskUtils.getResponseEntity(TaskConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaskUtils.getResponseEntity(TaskConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteTask(Long id) {
        try {
            Optional<Task> optional = taskDao.findById(id);
            if (!optional.isEmpty()) {
                taskDao.deleteById(id);
                return TaskUtils.getResponseEntity("Task deleted successfully", HttpStatus.OK);
            }
            return TaskUtils.getResponseEntity("Task id does not exist", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(TaskConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* Private helper functions */
    private Task getTaskFromMap(Long id, Map<String, String> requestMap, boolean isUpdate) {
        Task task = new Task();
        if (isUpdate) {
            task.setId(id);
        }

        task.setTitle(requestMap.get("title"));
        task.setDescription(requestMap.get("description"));
        task.setCompleted(Boolean.parseBoolean(requestMap.get("completed")));
        return task;
    }

    private boolean validateTaskMap(Map<String, String> requestMap) {
        return requestMap.containsKey("title") && requestMap.containsKey("description")
                && requestMap.containsKey("completed");
    }
}
