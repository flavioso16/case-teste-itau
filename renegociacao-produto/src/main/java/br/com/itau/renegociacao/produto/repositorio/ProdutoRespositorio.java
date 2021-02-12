package br.com.itau.renegociacao.produto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.itau.renegociacao.produto.modelo.Produto;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 6:19 PM
 */
@Repository
public interface ProdutoRespositorio extends JpaRepository<Produto, Long> {

    @Query("select p from Produto p where :tipo is null or p.tipo = :tipo")
    List<Produto> findByTipo(String tipo);

}
