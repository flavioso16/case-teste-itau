package br.com.itau.renegociacao.produto.exception;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 7:13 PM
 */
@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {

    private BindingResult bindingResult;

}
