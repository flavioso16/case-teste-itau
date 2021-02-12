package br.com.itau.renegociacao.produto.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 7:04 PM
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problema {

    private Integer status;
    private String detalhe;

    private String mensagem;
    private LocalDateTime data;
    private List<Campo> campos;

    @Getter
    @Builder
    public static class Campo {
        private String nome;
        private String mensagem;
    }

}
