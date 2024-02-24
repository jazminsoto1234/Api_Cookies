package com.example.proyectofinal;

import com.example.proyectofinal.Galleta.Exceptions.GalletaNotFound;
import com.example.proyectofinal.HandlyError.DataBaseException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /*@ExceptionHandler(value = {DataAccessException.class})
    protected ResponseEntity<Object> handleDataAccessException(RuntimeException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse("Error en la aplicacion", ex.getMessage());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }*/



    @ExceptionHandler(GalletaNotFound.class)
    protected ResponseEntity<Object> GalletaNotFoundExecption(GalletaNotFound ge, WebRequest request){
        String bodyOfResponse = "Galleta Not Found";
        ErrorResponse errorResponse = new ErrorResponse(bodyOfResponse, ge.getMessage());

        return handleExceptionInternal(ge, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataBaseException.class)
    protected ResponseEntity<Object> HandleDataBaseException(DataBaseException de, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse("Error en la aplicacion", de.getMessage());
        return handleExceptionInternal(de, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


}