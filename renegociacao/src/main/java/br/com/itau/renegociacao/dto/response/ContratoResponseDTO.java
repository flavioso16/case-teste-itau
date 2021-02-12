package br.com.itau.renegociacao.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 6:27 PM
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContratoResponseDTO {

    private Long id;
    private Long idCliente;
    private LocalDateTime dataContrato;
    private List<ParcelaResponseDTO> parcelas = new ArrayList<>();
    private ProductResponseDTO produto;

    public BigDecimal getValorTotalContrato() {
        return parcelas.stream()
                .map(ParcelaResponseDTO::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getValorPendentePagamento() {
        return parcelas.stream()
                .filter(ParcelaResponseDTO::parcelasNaoPagas)
                .map(ParcelaResponseDTO::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Integer getTotalParcelas() {
        return parcelas.size();
    }

    public Long getTotalParcelasPendentes() {
        return parcelas.stream()
                .filter(ParcelaResponseDTO::parcelasNaoPagas)
                .count();
    }

}
