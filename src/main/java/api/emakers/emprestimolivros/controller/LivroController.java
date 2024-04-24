package api.emakers.emprestimolivros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.emakers.emprestimolivros.Dto.PostLivro;
import api.emakers.emprestimolivros.Dto.UpdateLivro;
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
    public ResponseEntity<Object> cadastrarLivro(@RequestBody @Valid PostLivro dados) {
        livroService.cadastrarLivro(dados);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/livro/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody UpdateLivro dados) {
        var livroAtualizado = livroService.atualizarLivro(id, dados);
        
        return ResponseEntity.ok().body(livroAtualizado);
    }
    
    @DeleteMapping("/livro/{id}")
    public ResponseEntity<Object> excluirLogicamente(@PathVariable Long id){
        livroService.delecaoLogica(id);
        return ResponseEntity.noContent().build();
    }
    
}
