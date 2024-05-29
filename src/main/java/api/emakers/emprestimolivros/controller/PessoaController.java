package api.emakers.emprestimolivros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.emakers.emprestimolivros.dto.pessoa.LivroPessoaResponse;
import api.emakers.emprestimolivros.dto.pessoa.PessoaPorIdReponse;
import api.emakers.emprestimolivros.dto.pessoa.PessoaResponse;
import api.emakers.emprestimolivros.dto.pessoa.PostPessoa;
import api.emakers.emprestimolivros.dto.pessoa.UpdatePessoa;
import api.emakers.emprestimolivros.model.Pessoa;
import api.emakers.emprestimolivros.service.LivroService;
import api.emakers.emprestimolivros.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pessoas", description = "End-points relacionado a pessoas que permite cadastrar, buscar, atualizar e excluir pessoas, além de emprestimo e devolução de livros")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private LivroService livroService;

    @PostMapping("/pessoa")
    @Operation(summary = "Cadastrar uma nova pessoa")
    public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody @Valid PostPessoa dado) {
        
        pessoaService.cadastrarPessoa(dado);

        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    @Operation(summary = "Buscar todas as pessoas")
    public ResponseEntity<List<PessoaResponse>> buscarTodasPessoas() {

        List<PessoaResponse> pessoas = pessoaService.buscarTodasPessoas();

        return ResponseEntity.ok().body(pessoas);
    }
    
    @GetMapping("/pessoa/{id}")
    @Operation(summary = "Buscar pessoa por ID")
    public ResponseEntity<PessoaPorIdReponse> buscarPessoaPorId(@PathVariable Long id) {
        
        PessoaPorIdReponse pessoa = pessoaService.buscarPessoaPorId(id);

        return ResponseEntity.ok().body(pessoa);
    }

    @GetMapping("/pessoa/{id}/livros")
    @Operation(summary = "Buscar livros de uma pessoa por ID")
    public ResponseEntity<List<LivroPessoaResponse>> buscarLivrosPessoa(@PathVariable Long id) {
        
        var livros = livroService.buscarLivrosPorPessoa(id);
        
        return ResponseEntity.ok().body(livros);
    }
    
    @PutMapping("/pessoa/{id}")
    @Operation(summary = "Atualizar dados de uma pessoa")
    public ResponseEntity<Object> atualizarPessoa(@PathVariable Long id, @RequestBody UpdatePessoa dado) {
        
        pessoaService.atualizarPessoa(id, dado);
        
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/pessoa/{pessoaId}/emprestimo/{livroId}")
    @Operation(summary = "Fazer emprestimo de um livro")
    public ResponseEntity<Object> pegarLivroEmprestado(@PathVariable Long pessoaId, @PathVariable Long livroId) {
        
        livroService.emprestimoLivro(pessoaId, livroId);
        
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/pessoa/{pessoaId}/devolucao/{livroId}")
    @Operation(summary = "Fazer devolução de um livro")
    public ResponseEntity<Object> devolverLivro(@PathVariable Long pessoaId, @PathVariable Long livroId) {
        
        livroService.devolucaoLivro(pessoaId, livroId);
        
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/pessoa/{id}")
    @Operation(summary = "Excluir uma pessoa")
    public ResponseEntity<Object> deletarPessoa(@PathVariable Long id) {
        
        pessoaService.deletarPessoa(id);
        
        return ResponseEntity.noContent().build();
    }
}
