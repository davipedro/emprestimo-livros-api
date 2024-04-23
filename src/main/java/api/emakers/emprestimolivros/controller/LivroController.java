package api.emakers.emprestimolivros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.emakers.emprestimolivros.Dto.PostLivro;
import api.emakers.emprestimolivros.model.Livro;
import api.emakers.emprestimolivros.service.LivroService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;
    
    @GetMapping
    public ResponseEntity<List<Livro>> buscarTodosLivros() {
        List<Livro> livros = livroService.buscarTodosLivros();
        
        return ResponseEntity.ok().body(livros);
    }

    @GetMapping("/livro/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarLivroPorId(id);

        return ResponseEntity.ok().body(livro);
    }

    @PostMapping("/livro")
    public ResponseEntity<Object> cadastrarLivro(@RequestBody @Valid PostLivro data) {
        livroService.cadastrarLivro(data);
        return ResponseEntity.noContent().build();
    }
    
    
}
