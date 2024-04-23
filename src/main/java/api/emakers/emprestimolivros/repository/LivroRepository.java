package api.emakers.emprestimolivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.emakers.emprestimolivros.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
    
}
