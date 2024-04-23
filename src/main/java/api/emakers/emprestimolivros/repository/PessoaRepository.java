package api.emakers.emprestimolivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.emakers.emprestimolivros.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}
