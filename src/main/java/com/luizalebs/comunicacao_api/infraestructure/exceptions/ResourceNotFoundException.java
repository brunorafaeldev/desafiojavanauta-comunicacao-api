package com.luizalebs.comunicacao_api.infraestructure.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String menagem) {
        super(menagem);
    }

    public ResourceNotFoundException (String mensagem, Throwable cause) {
        super(mensagem, cause);
    }


}
