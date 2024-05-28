package api.emakers.emprestimolivros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.emakers.emprestimolivros.dto.livro.LivroResponse;
import api.emakers.emprestimolivros.dto.livro.PostLivro;
import api.emakers.emprestimolivros.dto.livro.UpdateLivro;
import api.emakers.emprestimolivros.dto.pessoa.LivroPessoaResponse;
import api.emakers.emprestimolivros.infra.exceptions.LivroNaoEncontradoException;
import api.emakers.emprestimolivros.infra.exceptions.PessoaNaoEncontradaException;
import api.emakers.emprestimolivros.infra.validacoes.LivroValidador;
import api.emakers.emprestimolivros.model.Livro;
import api.emakers.emprestimolivros.model.Pessoa;
import api.emakers.emprestimolivros.repository.LivroRepository;
import api.emakers.emprestimolivros.repository.PessoaRepository;

@Service
public class LivroService {
    
    @Autowired
    private List<LivroValidador> livroValidadores;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<LivroResponse> buscarTodosLivros() {
        List<Livro> livrosBuscados = livroRepository.findAll();

        //retorna o mapeamento de livros para livroResponse
        return livrosBuscados.stream().map(livro -> new LivroResponse(
            livro.getId(),
            livro.getNome(),
            livro.getAutor(),
            livro.getDataLancamento(),
            livro.getQuantidadeDisponivel()
        )).toList();
    }

    public Livro buscarLivroPorId(Long id) {
        return livroRepository.findById(id)
        .orElseThrow(() -> new LivroNaoEncontradoException("Livro não pode ser encontrado"));
    }

    public void cadastrarLivro(PostLivro data) {
        Livro livro = new Livro(
            data.nome(),
            data.autor(),
            data.data(),
            data.quantidadeDisponivel()
        );

        livroRepository.save(livro);
    }

    public void deletarLivro(Long id) {
        var livro = livroRepository.findById(id)
        .orElseThrow(() -> new LivroNaoEncontradoException("O livro não foi encontrado para deleção"));

        livroRepository.delete(livro);
    }

    public LivroResponse atualizarLivro(Long id, UpdateLivro dados) {
        var livro = livroRepository.findById(id)
        .orElseThrow(() -> new LivroNaoEncontradoException("O livro não foi encontrado para deleção"));
    
        if (dados.nome() != null) livro.setNome(dados.nome());
        if (dados.autor() != null) livro.setAutor(dados.autor());
        if (dados.data() != null) livro.setDataLancamento(dados.data());
        if (dados.quantidade() != null) livro.setQuantidadeDisponivel(dados.quantidade());

        var livroAtualizado = livroRepository.save(livro);

        return new LivroResponse(
            livroAtualizado.getId(),
            livroAtualizado.getNome(),
            livroAtualizado.getAutor(),
            livroAtualizado.getDataLancamento(),
            livroAtualizado.getQuantidadeDisponivel()
        );
    }

    public void emprestimoLivro(Long pessoaId, Long livroId) {
        Livro livro = livroRepository.findById(livroId)
        .orElseThrow(() -> new LivroNaoEncontradoException("O livro não pode ser encontrado"));
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
        .orElseThrow(() -> new PessoaNaoEncontradaException("A pessoa não pode ser encontrada"));

        livroValidadores.forEach(v -> v.validar(livro));

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
        pessoa.getLivros().add(livro);
    
        livro.getPessoas().add(pessoa);

        pessoaRepository.save(pessoa);
    }

    public List<LivroPessoaResponse> buscarLivrosPorPessoa(Long id) {
        List<Livro> livros = livroRepository.findLivrosByPessoaId(id)
        .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada"));

        return livros.stream().map(livro -> new LivroPessoaResponse(
            livro.getId(),
            livro.getNome(),
            livro.getAutor(),
            livro.getDataLancamento()
        )).toList();
    }

    public void devolucaoLivro(Long pessoaId, Long livroId) {
        Livro livro = livroRepository.findById(livroId)
        .orElseThrow(() -> new LivroNaoEncontradoException("O livro não pode ser encontrado"));
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
        .orElseThrow(() -> new PessoaNaoEncontradaException("A pessoa não pode ser encontrada"));

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
        
        pessoaRepository.findLivroByPessoaId(pessoaId, livroId).orElseThrow(
            () -> new LivroNaoEncontradoException
            ("O livro não pode ser encontrado para devolução: pessoa de id " + pessoaId + " não possui o livro de id " + livroId + " emprestado"));
        pessoa.getLivros().remove(livro);

        livro.getPessoas().remove(pessoa);

        pessoaRepository.save(pessoa);
    }
    
}
