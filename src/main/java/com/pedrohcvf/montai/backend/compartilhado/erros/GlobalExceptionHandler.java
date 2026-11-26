package com.pedrohcvf.montai.backend.compartilhado.erros;

import com.pedrohcvf.montai.backend.compartilhado.erros.custom.NaoEcontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail tratar(IllegalArgumentException e){
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problema.setProperty("timestamp", Instant.now());
        return problema;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail capturarErroGenerico(Exception e){
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno, tente novamente mais tarde.");
        problema.setProperty("timestamp", Instant.now());
        log.error("Erro inesperado", e);
        return problema;
    }

    @ExceptionHandler(NaoEcontradoException.class)
    public ProblemDetail naoEncontradoException(NaoEcontradoException e){
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problema.setProperty("timestamp", Instant.now());
        return problema;
    }

}
