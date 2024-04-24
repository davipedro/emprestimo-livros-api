package api.emakers.emprestimolivros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.emakers.emprestimolivros.model.Pessoa;
import api.emakers.emprestimolivros.service.PessoaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


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
    
    
}
