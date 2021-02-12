package br.com.itau.renegociacao.produto.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.itau.renegociacao.produto.base.CustomAbstractMockMvcTest;
import br.com.itau.renegociacao.produto.constants.SqlScriptsConstants;
import br.com.itau.renegociacao.produto.dto.request.ProdutoRequestDTO;
import br.com.itau.renegociacao.produto.repositorio.ProdutoRespositorio;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/12/21 1:25 AM
 */
@Sql(scripts = { SqlScriptsConstants.PRODUTOS})
@Sql(scripts = { SqlScriptsConstants.CLEAR }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProdutoControllerMockMvcTest extends CustomAbstractMockMvcTest {

    @Autowired
    private ProdutoRespositorio produtoRespositorio;

    @Test
    public void aoBuscarProdutosDeveRetornarComSucesso() throws Exception {
        mockMvc.perform(get("/produto"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void aoBuscarProdutoPorIdDeveRetornarComSucesso() throws Exception {
        final Long idProduto = produtoRespositorio.findAll().stream().findFirst().get().getId();
        mockMvc.perform(get("/produto/" + idProduto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idProduto));
    }

    @Test
    public void aoBuscarProdutoInvalidoDeveRetornarNotFound() throws Exception {
        mockMvc.perform(get("/produto/100"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem")
                        .value("Recurso do tipo Produto de ID 100 não encontrado."));
    }

    @Test
    public void dadoUmProdutoValidoDeveRetornarCreated() throws Exception {
        final ProdutoRequestDTO produto = criaProdutoPadrao();
        mockMvc.perform(post("/produto", produto))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    private ProdutoRequestDTO criaProdutoPadrao() {
        return ProdutoRequestDTO.builder()
                .nome("Crédito Pessoal 3")
                .tipo("credito-pessoal 3")
                .descricao("Para quando você precisa de dinheiro de forma fácil e rápida 3.")
                .taxaAnual(BigDecimal.valueOf(164.45))
                .taxaMensal(BigDecimal.valueOf(8.32))
                .taxaDiariaAtraso(BigDecimal.valueOf(50))
                .valorMultaAtraso(BigDecimal.valueOf(0.03))
                .build();
    }

    @Test
    public void dadoUmParametroInvalidoCriarProdutoDeveRetornarBadRequest() throws Exception {
        final ProdutoRequestDTO produto = criaProdutoPadrao();
        produto.setNome(null);
        mockMvc.perform(post("/produto", produto))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}