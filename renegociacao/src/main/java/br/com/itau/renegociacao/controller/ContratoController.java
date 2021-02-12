package br.com.itau.renegociacao.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.renegociacao.dto.response.ContratoResponseDTO;
import br.com.itau.renegociacao.servico.ContratoServico;
import br.com.itau.renegociacao.servico.ProductService;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 1:36 PM
 */
@RestController
@RequestMapping("contrato")
public class ContratoController {

    @Autowired
    private ContratoServico contratoServico;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public List<ContratoResponseDTO> listar(@RequestParam(required = true) Long idCliente) {
        return contratoServico.listar(idCliente).stream()
            .map(contratoServico::converterContratoParaDTO)
            .collect(Collectors.toList());
    }

}
