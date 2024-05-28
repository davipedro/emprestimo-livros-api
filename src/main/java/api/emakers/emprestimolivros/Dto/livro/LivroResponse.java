package api.emakers.emprestimolivros.dto.livro;

import java.time.LocalDate;

public record LivroResponse(Long id, String nome, String autor, LocalDate data_lancamento, Integer quantidade_disponivel) {
    
}