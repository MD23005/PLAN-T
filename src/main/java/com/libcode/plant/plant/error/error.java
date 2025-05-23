package com.libcode.plant.plant.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class error {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model modelo) {
        modelo.addAttribute("mensaje: ",e.getMessage());
        return "error"; // Return the name of the error view
    }
    
}
