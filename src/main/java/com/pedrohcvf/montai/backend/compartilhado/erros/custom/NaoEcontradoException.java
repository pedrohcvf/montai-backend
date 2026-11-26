package com.pedrohcvf.montai.backend.compartilhado.erros.custom;

public class NaoEcontradoException extends RuntimeException {
    public NaoEcontradoException(String message) {
        super(message);
    }
}
