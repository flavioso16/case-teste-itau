package br.com.itau.renegociacao.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.itau.renegociacao.model.StatusParcela;
import lombok.Data;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 4:44 PM
 */
@Data
public class ParcelaResponseDTO {

    private Long id;
    private LocalDateTime dataVencimento;
    private StatusParcela status;
    private BigDecimal valor;

    public Boolean parcelasNaoPagas() {
        return !StatusParcela.PAGA.equals(status);
    }

}
