package com.rizal.StockApp.controllerAdvice;

import com.rizal.StockApp.exceptions.EmptyInputException;
import com.rizal.StockApp.exceptions.StockAppExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StockAppControllerAdvice {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity handleEmptyinputException(EmptyInputException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StockAppExceptions.class)
    public ResponseEntity handleStockException(StockAppExceptions stockAppExceptions){
        return new ResponseEntity(stockAppExceptions.getMessage(),HttpStatus.BAD_REQUEST);
    }



}
