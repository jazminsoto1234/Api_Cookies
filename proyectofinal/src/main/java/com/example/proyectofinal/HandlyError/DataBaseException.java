package com.example.proyectofinal.HandlyError;

public class DataBaseException extends RuntimeException {

    public DataBaseException(String mensaje){
        super(mensaje);
    }
    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
