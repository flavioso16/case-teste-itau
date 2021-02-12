package br.com.itau.renegociacao.produto.exception;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 7:29 PM
 */
public class EntidadeEmUsoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String message) {
        super(message);
    }

}

