package api.emakers.emprestimolivros.infra.validacoes.livro;

import org.springframework.stereotype.Component;

import api.emakers.emprestimolivros.infra.exceptions.LivroIndisponivelException;
import api.emakers.emprestimolivros.infra.validacoes.LivroValidador;
import api.emakers.emprestimolivros.model.Livro;

@Component
public class ValidarDisponibilidadeLivro implements LivroValidador{
    @Override
    public void validar(Livro livro) {
        if(livro.getQuantidadeDisponivel() == 0) {
            throw new LivroIndisponivelException("Todos os livros já foram emprestados");
        }
    }
}
