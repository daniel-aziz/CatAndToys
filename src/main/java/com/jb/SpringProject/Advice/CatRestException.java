package com.jb.SpringProject.Advice;

import com.jb.SpringProject.Exceptions.CatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CatRestException {
    @ExceptionHandler (value = {CatException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetailCat handleException(Exception e){
        return new ErrorDetailCat("Error:",e.getMessage());
    }

}
