package api.emakers.emprestimolivros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import api.emakers.emprestimolivros.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
    
    @Query("SELECT l FROM Pessoa p JOIN p.livros l WHERE p.id = :pessoaId")
    Optional<List<Livro>> findLivrosByPessoaId(@Param("pessoaId") Long pessoaId);
}
