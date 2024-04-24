package api.emakers.emprestimolivros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.emakers.emprestimolivros.Dto.pessoa.PostPessoa;
import api.emakers.emprestimolivros.model.Pessoa;
import api.emakers.emprestimolivros.service.PessoaService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    
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
    
    
}
