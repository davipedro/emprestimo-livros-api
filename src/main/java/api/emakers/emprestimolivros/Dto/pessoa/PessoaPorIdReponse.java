package api.emakers.emprestimolivros.dto.pessoa;

import java.util.List;

import api.emakers.emprestimolivros.model.Endereco;

public record PessoaPorIdReponse(Long id, String nome, Endereco endereco, List<LivroPessoaResponse> livros) {
    
}
