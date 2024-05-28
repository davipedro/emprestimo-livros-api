package api.emakers.emprestimolivros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import api.emakers.emprestimolivros.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    @EntityGraph(attributePaths = "livros")
    List<Pessoa> findAll();

    @Query("SELECT l FROM Livro l JOIN l.pessoas p WHERE p.id = :pessoaId AND l.id = :livroId")
    Optional<Pessoa> findLivroByPessoaId(@Param("pessoaId") Long pessoaId, @Param("livroId") Long livroId);
}
