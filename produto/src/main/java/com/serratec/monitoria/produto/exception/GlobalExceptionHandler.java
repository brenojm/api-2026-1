package com.serratec.monitoria.produto.exception;

import com.serratec.monitoria.produto.model.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(erro -> erro.getField() + ": " + erro.getDefaultMessage()).toList();
        ResponseEntity<ErroResposta> erroResposta = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResposta("BAD_REQUEST", erros));
        return erroResposta;
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> handleNotFound(ProdutoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroResposta("NOT_FOUND", List.of(ex.getMessage())));
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResposta> handleRegraNegocio(RegraNegocioException ex) {
        ResponseEntity<ErroResposta> erroResposta = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErroResposta("UNPROCESSABLE_ENTITY", List.of(ex.getMessage())));
        return erroResposta;
    }
}
