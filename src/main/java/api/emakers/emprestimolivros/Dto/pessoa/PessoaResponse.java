package api.emakers.emprestimolivros.dto.pessoa;

import api.emakers.emprestimolivros.model.Endereco;

public record PessoaResponse(Long id, String nome, Endereco endereco) {
    
}
