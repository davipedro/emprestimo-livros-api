package api.emakers.emprestimolivros.Dto.pessoa;

import jakarta.validation.constraints.NotBlank;

public record PostPessoa(
    @NotBlank
    String nome,
    @NotBlank 
    String logradouro,
    @NotBlank
    String bairro,
    @NotBlank
    String cep,
    @NotBlank
    String numero,
    String complemento,
    @NotBlank
    String cidade,
    @NotBlank
    String uf) {
    
}
