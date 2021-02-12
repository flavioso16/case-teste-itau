package br.com.itau.renegociacao.produto.exception;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 7:28 PM
 */
public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }

}

