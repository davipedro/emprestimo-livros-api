package api.emakers.emprestimolivros.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//embeddable permite que o endereco esteja dentro de uma tabela mas fora pelo código
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
    
}
