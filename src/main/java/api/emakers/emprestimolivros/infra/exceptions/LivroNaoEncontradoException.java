package api.emakers.emprestimolivros.infra.exceptions;

public class LivroNaoEncontradoException extends RuntimeException{
    
    public LivroNaoEncontradoException(){}
    
    public LivroNaoEncontradoException(String mensagemErro){
        super(mensagemErro);
    }
}
