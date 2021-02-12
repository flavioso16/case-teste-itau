package br.com.itau.renegociacao.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 4:44 PM
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "contrato")
public class Contrato {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Long id;
    
    @Column(name = "id_produto", nullable = false)
    private Long idProduto;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "data_contrato", nullable = false)
    private LocalDateTime dataContrato;

    @OneToMany(mappedBy = "contrato")
    private List<Parcela> parcelas = new ArrayList<>();

}
