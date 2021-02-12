package br.com.itau.renegociacao.produto.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.itau.renegociacao.produto.dto.request.ProdutoRequestDTO;
import br.com.itau.renegociacao.produto.dto.response.ProdutoResponseDTO;
import br.com.itau.renegociacao.produto.modelo.Produto;
import br.com.itau.renegociacao.produto.servico.ProdutoServico;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 1:36 PM
 */
@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoServico produtoServico;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{produtoId}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long produtoId) {
        return mapper.map(produtoServico.buscarOuFalhar(produtoId), ProdutoResponseDTO.class);
    }

    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return produtoServico.listar().stream()
            .map(p -> mapper.map(p, ProdutoResponseDTO.class))
            .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO salvar(@RequestBody @Valid ProdutoRequestDTO produtoDTO) {
        final Produto produto = mapper.map(produtoDTO, Produto.class);
        return mapper.map(produtoServico.salvar(produto), ProdutoResponseDTO.class);
    }

    @PutMapping("/{produtoId}")
    public ProdutoResponseDTO update(@PathVariable Long produtoId,
            @RequestBody @Valid ProdutoRequestDTO produtoDTO) {
        return mapper.map(produtoServico.alterar(produtoId, produtoDTO), ProdutoResponseDTO.class);
    }

    @GetMapping("/ofertas/{tipo}")
    @HystrixCommand(fallbackMethod = "ofertaPadrao")
    public List<ProdutoResponseDTO> listarOfertas(@PathVariable String tipo) {
        return produtoServico.listar(tipo).stream()
                .map(p -> mapper.map(p, ProdutoResponseDTO.class))
                .collect(Collectors.toList());
    }


    private List<ProdutoResponseDTO> ofertaPadrao(String tipo) {
        return Lists.newArrayList(ProdutoResponseDTO.builder()
                .id(1L)
                .nome("Crédito Pessoal")
                .tipo("credito-pessoal")
                .descricao("Para quando você precisa de dinheiro de forma fácil e rápida.")
                .taxaAnual(BigDecimal.valueOf(164.45))
                .taxaMensal(BigDecimal.valueOf(8.32))
                .taxaDiariaAtraso(BigDecimal.valueOf(50))
                .valorMultaAtraso(BigDecimal.valueOf(0.03))
                .build());
    }

}
