package api.emakers.emprestimolivros.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.emakers.emprestimolivros.dto.pessoa.LivroPessoaResponse;
import api.emakers.emprestimolivros.dto.pessoa.PessoaPorIdReponse;
import api.emakers.emprestimolivros.dto.pessoa.PessoaResponse;
import api.emakers.emprestimolivros.dto.pessoa.PostPessoa;
import api.emakers.emprestimolivros.dto.pessoa.UpdatePessoa;
import api.emakers.emprestimolivros.infra.exceptions.PessoaNaoEncontradaException;
import api.emakers.emprestimolivros.model.Endereco;
import api.emakers.emprestimolivros.model.Livro;
import api.emakers.emprestimolivros.model.Pessoa;
import api.emakers.emprestimolivros.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaResponse> buscarTodasPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        return pessoas.stream().map(pessoa -> new PessoaResponse(
            pessoa.getId(),
            pessoa.getNome(),
            pessoa.getEndereco()
        )).toList();
    }

    public PessoaPorIdReponse buscarPessoaPorId(Long id) {
        var Pessoa = pessoaRepository.findById(id)
        .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada"));

        List<LivroPessoaResponse> livros = Pessoa.getLivros().stream().map(livro -> new LivroPessoaResponse(
            livro.getId(),
            livro.getNome(),
            livro.getAutor(),
            livro.getDataLancamento()
        )).toList();

        return new PessoaPorIdReponse(
            Pessoa.getId(),
            Pessoa.getNome(),
            Pessoa.getEndereco(),
            livros
        );
    }

    public void cadastrarPessoa(PostPessoa dado) {

        var endereco = new Endereco(
            dado.endereco().getLogradouro(),
            dado.endereco().getBairro(),
            dado.endereco().getCep(),
            dado.endereco().getNumero(),
            dado.endereco().getComplemento(),
            dado.endereco().getCidade(),
            dado.endereco().getUf()
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

    public List<LivroPessoaResponse> buscarLivrosPessoa(Long id) {
        List<Livro> livros = pessoaRepository.findLivrosByPessoaId(id)
        .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada"));

        return livros.stream().map(livro -> new LivroPessoaResponse(
            livro.getId(),
            livro.getNome(),
            livro.getAutor(),
            livro.getDataLancamento()
        )).toList();
    }

    
}