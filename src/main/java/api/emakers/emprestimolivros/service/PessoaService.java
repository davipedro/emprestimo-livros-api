package api.emakers.emprestimolivros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.emakers.emprestimolivros.Dto.pessoa.PostPessoa;
import api.emakers.emprestimolivros.Dto.pessoa.UpdatePessoa;
import api.emakers.emprestimolivros.infra.exceptions.PessoaNaoEncontradaException;
import api.emakers.emprestimolivros.model.Endereco;
import api.emakers.emprestimolivros.model.Pessoa;
import api.emakers.emprestimolivros.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> buscarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id)
        .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada"));
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

    public void atualizarPessoa(Long id, UpdatePessoa dado) {
        var pessoa = pessoaRepository.findById(id)
        .orElseThrow(() -> new PessoaNaoEncontradaException());

        if (dado.nome() != null) pessoa.setNome(dado.nome());
        if (dado.logradouro() != null) pessoa.getEndereco().setLogradouro(dado.logradouro());
        if (dado.bairro() != null) pessoa.getEndereco().setBairro(dado.bairro());
        if (dado.cep() != null) pessoa.getEndereco().setCep(dado.cep());
        if (dado.numero() != null) pessoa.getEndereco().setNumero(dado.numero());
        if (dado.complemento() != null) pessoa.getEndereco().setComplemento(dado.complemento());
        if (dado.cidade() != null) pessoa.getEndereco().setCidade(dado.cidade());
        if (dado.uf() != null) pessoa.getEndereco().setUf(dado.uf());

        pessoaRepository.save(pessoa);
    }

    public void deletarPessoa(Long id) {
        var pessoa = pessoaRepository.findById(id)
        .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada"));

        pessoaRepository.delete(pessoa);
    }
    
}