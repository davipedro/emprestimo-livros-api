package api.emakers.emprestimolivros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.emakers.emprestimolivros.Dto.PostLivro;
import api.emakers.emprestimolivros.infra.exceptions.LivroNaoEncontradoException;
import api.emakers.emprestimolivros.model.Livro;
import api.emakers.emprestimolivros.repository.LivroRepository;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;

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
    
}
