package it.unical.demacs.informatica.userhandlerservice;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(new JSONObject(Map.of("error", "error while processing the request: " + e.getMessage())).toString(), HttpStatus.BAD_REQUEST);
    }
}
