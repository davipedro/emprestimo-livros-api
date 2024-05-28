package api.emakers.emprestimolivros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.emakers.emprestimolivros.Dto.livro.PostLivro;
import api.emakers.emprestimolivros.Dto.livro.UpdateLivro;
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

    public List<Livro> buscarTodosLivros() {
        return livroRepository.findAll();
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
            data.quantidade()
        );

        livroRepository.save(livro);
    }

    public void deletarLivro(Long id) {
        var livro = livroRepository.findById(id)
        .orElseThrow(() -> new LivroNaoEncontradoException("O livro não foi encontrado para deleção"));

        livroRepository.delete(livro);
    }

    public Livro atualizarLivro(Long id, UpdateLivro dados) {
        var livro = livroRepository.findById(id)
        .orElseThrow(() -> new LivroNaoEncontradoException("O livro não foi encontrado para deleção"));
    
        if (dados.nome() != null) livro.setNome(dados.nome());
        if (dados.autor() != null) livro.setAutor(dados.autor());
        if (dados.data() != null) livro.setDataLancamento(dados.data());
        if (dados.quantidade() != null) livro.setQuantidade(dados.quantidade());

        var livroAtualizado = livroRepository.save(livro);

        return livroAtualizado;
    }

    public void emprestimoLivro(Long pessoaId, Long livroId) {
        Livro livro = livroRepository.findById(livroId)
        .orElseThrow(() -> new LivroNaoEncontradoException("O livro não pode ser encontrado"));
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
        .orElseThrow(() -> new PessoaNaoEncontradaException("A pessoa não pode ser encontrada"));

        livroValidadores.forEach(v -> v.validar(livro));

        livro.setQuantidade(livro.getQuantidade() - 1);
        pessoa.getLivros().add(livro);

        pessoaRepository.save(pessoa);
        livroRepository.save(livro);
    }
    
}
