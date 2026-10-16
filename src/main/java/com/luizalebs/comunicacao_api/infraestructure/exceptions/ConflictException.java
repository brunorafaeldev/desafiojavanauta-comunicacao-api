package com.luizalebs.comunicacao_api.infraestructure.exceptions;

public class ConflictException extends RuntimeException{

    public ConflictException(String messagem){
        super(messagem);
    }

    public ConflictException(String messagem,Throwable cause){

        super(messagem,cause);
    }

}
