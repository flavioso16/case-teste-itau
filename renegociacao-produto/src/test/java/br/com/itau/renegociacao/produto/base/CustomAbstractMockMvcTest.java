package br.com.itau.renegociacao.produto.base;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 2/12/21 2:22 AM
 */
@ActiveProfiles("teste")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@TestPropertySource(properties = { "spring.config.location=classpath:application-teste.yml" })
public abstract class CustomAbstractMockMvcTest {

    @Autowired
    protected MockMvc mockMvc;

    protected MockHttpServletRequestBuilder post(String url, Object payLoad) {
        return MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(payLoad));
    }

    protected MockHttpServletRequestBuilder get(String url) {
        return MockMvcRequestBuilders.get(url);
    }

    protected static String convertObjectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new IllegalArgumentException("Não foi possível criar o json com o objeto de payload", e);
        }
    }

}
