package br.com.itau.renegociacao.produto.dto.response;

import java.math.BigDecimal;

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
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String tipo;
    private String descricao;
    private BigDecimal taxaAnual;
    private BigDecimal taxaMensal;
    private BigDecimal valorMultaAtraso;
    private BigDecimal taxaDiariaAtraso;

}
