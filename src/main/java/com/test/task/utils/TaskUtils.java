package com.test.task.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TaskUtils {
    private TaskUtils() {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }
}
