package api.emakers.emprestimolivros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.emakers.emprestimolivros.Dto.pessoa.PostPessoa;
import api.emakers.emprestimolivros.infra.exceptions.PessoaNaoEncontradaException;
import api.emakers.emprestimolivros.model.Endereco;
import api.emakers.emprestimolivros.model.Pessoa;
import api.emakers.emprestimolivros.repository.PessoaRepository;
import jakarta.validation.Valid;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> buscarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id)
        .orElseThrow(() -> new PessoaNaoEncontradaException());
    }

    public void cadastrarPessoa(PostPessoa dado) {

        var endereco = new Endereco(
            dado.logradouro(),
            dado.bairro(),
            dado.cep(),
            dado.numero(),
            dado.complemento(),
            dado.cidade(),
            dado.uf()
        );

        Pessoa pessoa = new Pessoa(
            dado.nome(),
            endereco
        );

        pessoaRepository.save(pessoa);
    }
    
}
