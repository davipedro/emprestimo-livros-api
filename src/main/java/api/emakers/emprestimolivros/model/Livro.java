package api.emakers.emprestimolivros.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "livros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45)
    private String nome;
    @Column(length = 45)
    private String autor;
    private int quantidade;
    @Column(name = "data_lancamento")
    LocalDate dataLancamento;
    
    @ManyToMany
    @JoinTable(
        name = "Livro_Pessoa",
        joinColumns = 
        @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "pessoa_id")
        )
    private List<Pessoa> pessoas;
        
    public Livro(String nome, String autor, LocalDate data, int quantidade) {
        this.nome = nome;
        this.autor = autor;
        this.dataLancamento = data;
        this.quantidade = quantidade;
    }
        
}