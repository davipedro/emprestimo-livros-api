package api.emakers.emprestimolivros.Dto.pessoa;

import api.emakers.emprestimolivros.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostPessoa(
    @NotBlank
    String nome,
    @NotNull 
    Endereco endereco) {
}
