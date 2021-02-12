package br.com.itau.renegociacao.produto.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 6:27 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String tipo;

    @NotBlank
    private String descricao;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaAnual;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaMensal;

    @NotNull
    @PositiveOrZero
    private BigDecimal valorMultaAtraso;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaDiariaAtraso;

}
