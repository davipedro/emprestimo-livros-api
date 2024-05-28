package api.emakers.emprestimolivros.dto.pessoa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroPessoaResponse(Long id, String nome, String autor, LocalDate data_lancamento) {
    
}
