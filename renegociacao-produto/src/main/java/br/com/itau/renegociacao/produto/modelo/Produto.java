package br.com.itau.renegociacao.produto.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "produto")
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "taxa_anual", nullable = false)
    private BigDecimal taxaAnual;

    @Column(name = "taxa_mensal", nullable = false)
    private BigDecimal taxaMensal;

    @Column(name = "valor_multa_atraso", nullable = false)
    private BigDecimal valorMultaAtraso;

    @Column(name = "taxa_diaria_atraso", nullable = false)
    private BigDecimal taxaDiariaAtraso;

}
