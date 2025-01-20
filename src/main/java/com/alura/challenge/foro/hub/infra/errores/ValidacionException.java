package com.alura.challenge.foro.hub.infra.errores;

public class ValidacionException extends RuntimeException{

    public ValidacionException(String mensaje){
        super(mensaje);
    }
}
