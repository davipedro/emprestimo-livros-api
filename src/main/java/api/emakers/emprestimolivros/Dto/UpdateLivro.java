package api.emakers.emprestimolivros.Dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateLivro(String nome, String autor, LocalDate data, Integer quantidade) {
    
}
