package api.emakers.emprestimolivros.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostLivro (@NotBlank String nome, @NotBlank String autor, @NotNull LocalDate data, @NotNull int quantidade){
    
}
