package br.com.itau.renegociacao.exception;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 7:29 PM
 */
public class EntidadeNaoEncontradaException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

    public EntidadeNaoEncontradaException(Class clazz, Long id) {
        super(String.format("Recurso do tipo %s de ID %d não encontrado.", clazz.getSimpleName(), id));
    }

}

