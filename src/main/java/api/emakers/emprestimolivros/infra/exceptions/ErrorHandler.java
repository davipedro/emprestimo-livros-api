package api.emakers.emprestimolivros.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    
    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<Object> livroNaoEncontrado(LivroNaoEncontradoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public ResponseEntity<Object> pessoaNaoEncontrada(PessoaNaoEncontradaException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
