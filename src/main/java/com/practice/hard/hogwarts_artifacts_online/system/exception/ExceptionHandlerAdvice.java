package com.practice.hard.hogwarts_artifacts_online.system.exception;

import com.practice.hard.hogwarts_artifacts_online.artifact.ArtifactNotFoundException;
import com.practice.hard.hogwarts_artifacts_online.system.Result;
import com.practice.hard.hogwarts_artifacts_online.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ArtifactNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handleArtifactNotFoundException(ArtifactNotFoundException exception) {
        return new Result(false, StatusCode.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        List<ObjectError> errors= ex.getBindingResult().getAllErrors();

        HashMap<String,String> map= new HashMap<>(errors.size());

        errors.forEach((error)->{

            String key= ((FieldError) error).getField();

            String val= error.getDefaultMessage();

            map.put(key, val);
        });
        return new Result(false,  StatusCode.INVALID_ARGUMENT, "Provided Arguments are invalid!", map);
    }
}
