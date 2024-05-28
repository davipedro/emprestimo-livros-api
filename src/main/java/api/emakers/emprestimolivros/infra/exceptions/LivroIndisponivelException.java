package api.emakers.emprestimolivros.infra.exceptions;

public class LivroIndisponivelException extends RuntimeException{

    public LivroIndisponivelException(){}

    public LivroIndisponivelException(String mensagem){
        super(mensagem);
    }
    
}
