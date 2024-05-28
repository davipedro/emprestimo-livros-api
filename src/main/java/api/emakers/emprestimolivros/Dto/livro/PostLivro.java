package api.emakers.emprestimolivros.dto.livro;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostLivro (
    @NotBlank 
    String nome, 
    @NotBlank 
    String autor, 
    @NotNull 
    LocalDate data, 
    @NotNull
    @Min(1)
    @JsonAlias("quantidade_disponivel")
    Integer quantidadeDisponivel){
}
