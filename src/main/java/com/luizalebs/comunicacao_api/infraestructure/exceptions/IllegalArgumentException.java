package com.luizalebs.comunicacao_api.infraestructure.exceptions;

public class IllegalArgumentException extends  RuntimeException {

    public IllegalArgumentException(String mensagem){
        super(mensagem);
    }

    public IllegalArgumentException(String mensage, Throwable cause){

        super(mensage, cause);
    }

}
