package api.emakers.emprestimolivros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.emakers.emprestimolivros.Dto.pessoa.PostPessoa;
import api.emakers.emprestimolivros.Dto.pessoa.UpdatePessoa;
import api.emakers.emprestimolivros.model.Livro;
import api.emakers.emprestimolivros.model.Pessoa;
import api.emakers.emprestimolivros.service.LivroService;
import api.emakers.emprestimolivros.service.PessoaService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private LivroService livroService;

    @Autowired
    
    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarTodasPessoas() {

        List<Pessoa> pessoas = pessoaService.buscarTodasPessoas();

        return ResponseEntity.ok().body(pessoas);
    }
    
    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);

        return ResponseEntity.ok().body(pessoa);
    }
    
    @PostMapping("/pessoa")
    public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody @Valid PostPessoa dado) {
        
        pessoaService.cadastrarPessoa(dado);

        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/pessoa/{id}")
    public ResponseEntity<Object> atualizarPessoa(@PathVariable Long id, @RequestBody UpdatePessoa dado) {
        
        pessoaService.atualizarPessoa(id, dado);
        
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/pessoa/{id}")
    public ResponseEntity<Object> deletarPessoa(@PathVariable Long id) {
        
        pessoaService.deletarPessoa(id);
        
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/pessoa/{pessoaId}/emprestimo/{livroId}")
    public ResponseEntity<Object> pegarLivroEmprestado(@PathVariable Long pessoaId, @PathVariable Long livroId) {
        
        livroService.emprestimoLivro(pessoaId, livroId);
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pessoa/{id}/livros")
    public ResponseEntity<List<Livro>> buscarLivrosPessoa(@PathVariable Long id) {
        
        var livros = pessoaService.buscarLivrosPessoa(id);
        
        return ResponseEntity.ok().body(livros);
    }
    
}
