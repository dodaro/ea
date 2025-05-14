package it.unical.inf.ea.uniprjms.teacher.config;

import it.unical.inf.ea.uniprjms.domain.dto.ServiceError;
import it.unical.inf.ea.uniprjms.shared.config.handler.GlobalExceptionHandler;
import it.unical.inf.ea.uniprjms.teacher.exception.TeacherException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class TeacherExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(TeacherException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceError onResourceNotFoundException(WebRequest req, EntityNotFoundException ex){
        return errorResponse(req, ex.getMessage());
    }
}