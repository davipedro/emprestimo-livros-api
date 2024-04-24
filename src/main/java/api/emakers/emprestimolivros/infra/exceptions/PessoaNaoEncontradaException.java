package api.emakers.emprestimolivros.infra.exceptions;

public class PessoaNaoEncontradaException extends RuntimeException{
    
    public PessoaNaoEncontradaException(){}

    public PessoaNaoEncontradaException(String mensagemErro){
        super(mensagemErro);
    }
}
