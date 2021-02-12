package br.com.itau.renegociacao.servico;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.itau.renegociacao.dto.response.ProductResponseDTO;
import br.com.itau.renegociacao.exception.EntidadeNaoEncontradaException;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/11/21 9:54 PM
 */
@Service
public class ProductService {

    @Value("${custom.product.url}")
    private String productUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    public ProductResponseDTO getProduct(final Long produtoId) {

        Map<String, String> params = new HashMap<>();
        params.put("produtoId", produtoId.toString()) ;

        final ProductResponseDTO product = restTemplate.getForEntity(
                productUrl + "/{produtoId}", ProductResponseDTO.class, params).getBody();
        if(product == null) {
            throw new EntidadeNaoEncontradaException("Não foi encontado um produto com o ID informado.");
        }
        return product;
    }

}
