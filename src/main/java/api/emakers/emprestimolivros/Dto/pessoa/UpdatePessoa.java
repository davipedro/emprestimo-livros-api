package api.emakers.emprestimolivros.Dto.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdatePessoa(
    String nome,
    String logradouro,
    String bairro,
    String cep,
    String numero,
    String complemento,
    String cidade,
    String uf) {
    
}
