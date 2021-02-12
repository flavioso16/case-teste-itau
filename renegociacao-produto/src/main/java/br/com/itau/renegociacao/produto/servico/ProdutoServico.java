package br.com.itau.renegociacao.produto.servico;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.itau.renegociacao.produto.dto.request.ProdutoRequestDTO;
import br.com.itau.renegociacao.produto.exception.EntidadeNaoEncontradaException;
import br.com.itau.renegociacao.produto.modelo.Produto;
import br.com.itau.renegociacao.produto.repositorio.ProdutoRespositorio;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 6:19 PM
 */
@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRespositorio respositorio;

    @Autowired
    public ModelMapper mapper;

    public List<Produto> buscarPorId(final String tipo) {
        return respositorio.findByTipo(tipo);
    }

    public List<Produto> listar() {
        return respositorio.findAll();
    }

    public List<Produto> listar(final String tipo) {
        return respositorio.findByTipo(tipo);
    }

    @Transactional
    public Produto salvar(final Produto produto) {
        return respositorio.save(produto);
    }

    @Transactional
    public Produto alterar(final Long produtoId, final ProdutoRequestDTO produtoRequestDTO) {
        Produto produto =  buscarOuFalhar(produtoId);
        mapper.map(produtoRequestDTO, produto);
        return produto;
    }

    public Produto buscarOuFalhar(final Long produtoId) {
        return respositorio.findById(produtoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(Produto.class, produtoId));
    }

}
