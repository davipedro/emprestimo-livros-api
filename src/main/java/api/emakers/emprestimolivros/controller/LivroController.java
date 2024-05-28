package api.emakers.emprestimolivros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.emakers.emprestimolivros.dto.livro.LivroResponse;
import api.emakers.emprestimolivros.dto.livro.PostLivro;
import api.emakers.emprestimolivros.dto.livro.UpdateLivro;
import api.emakers.emprestimolivros.model.Livro;
import api.emakers.emprestimolivros.service.LivroService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;
    
    @GetMapping
    public ResponseEntity<List<LivroResponse>> buscarTodosLivros() {
        List<LivroResponse> livros = livroService.buscarTodosLivros();
        
        return ResponseEntity.ok().body(livros);
    }

    @GetMapping("/livro/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarLivroPorId(id);

        return ResponseEntity.ok().body(livro);
    }

    @PostMapping("/livro")
    public ResponseEntity<Object> cadastrarLivro(@RequestBody @Valid PostLivro dados) {
        livroService.cadastrarLivro(dados);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/livro/{id}")
    public ResponseEntity<LivroResponse> atualizarLivro(@PathVariable Long id, @RequestBody UpdateLivro dados) {
        LivroResponse livroAtualizado = livroService.atualizarLivro(id, dados);
        
        return ResponseEntity.ok().body(livroAtualizado);
    }
    
    @DeleteMapping("/livro/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id){
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
    
}
