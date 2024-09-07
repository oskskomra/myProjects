package osk.sko.WebCalendar.event.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EventAdvice {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<String> handleWrongData(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoEventFoundException.class)
    ResponseEntity<NoEventFoundException> handleNoEventFound(NoEventFoundException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @ExceptionHandler(NoEventExists.class)
    ResponseEntity<Map<String, Object>> handleNoEventExists(NoEventExists e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "The event doesn't exist!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }



}
