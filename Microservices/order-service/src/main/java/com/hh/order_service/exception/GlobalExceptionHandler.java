package com.hh.order_service.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(ProductOutOfStockException.class)
    @ResponseBody
    public String handleProductNotFound(ProductOutOfStockException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex) {
        return "An unexpected error occurred: " + ex.getMessage();
    }
}
