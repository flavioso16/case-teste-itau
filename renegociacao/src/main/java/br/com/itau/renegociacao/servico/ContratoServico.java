package br.com.itau.renegociacao.servico;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.renegociacao.dto.response.ContratoResponseDTO;
import br.com.itau.renegociacao.dto.response.ProductResponseDTO;
import br.com.itau.renegociacao.model.Contrato;
import br.com.itau.renegociacao.repositorio.ContratoRepositorio;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 6:19 PM
 */
@Service
public class ContratoServico {

    @Autowired
    private ContratoRepositorio respositorio;

    @Autowired
    private ProductService productService;

    @Autowired
    public ModelMapper mapper;

    public List<Contrato> listar(final Long idCliente) {
        return respositorio.findByIdCliente(idCliente);
    }

    public ContratoResponseDTO converterContratoParaDTO(Contrato contrato) {
        ProductResponseDTO product = productService.getProduct(contrato.getIdProduto());
        final ContratoResponseDTO contratoDTO = mapper.map(contrato, ContratoResponseDTO.class);
        contratoDTO.setProduto(product);
        return contratoDTO;
    }

}
