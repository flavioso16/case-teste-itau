package br.com.itau.renegociacao.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itau.renegociacao.model.Contrato;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 6:19 PM
 */
@Repository
public interface ContratoRepositorio extends JpaRepository<Contrato, Long> {

    List<Contrato> findByIdCliente(Long idCliente);

}
