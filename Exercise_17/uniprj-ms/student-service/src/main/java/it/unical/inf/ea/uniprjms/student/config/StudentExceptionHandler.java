package it.unical.inf.ea.uniprjms.student.config;

import it.unical.inf.ea.uniprjms.domain.dto.ServiceError;
import it.unical.inf.ea.uniprjms.shared.config.handler.GlobalExceptionHandler;
import it.unical.inf.ea.uniprjms.student.exception.StudentException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class StudentExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(StudentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceError onResourceNotFoundException(WebRequest req, EntityNotFoundException ex){
        return errorResponse(req, ex.getMessage());
    }
}