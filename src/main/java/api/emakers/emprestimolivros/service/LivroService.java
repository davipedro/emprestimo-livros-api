package api.emakers.emprestimolivros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.emakers.emprestimolivros.model.Livro;
import api.emakers.emprestimolivros.repository.LivroRepository;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> buscarTodosLivros() {
        return livroRepository.findAll();
    }
    
}
