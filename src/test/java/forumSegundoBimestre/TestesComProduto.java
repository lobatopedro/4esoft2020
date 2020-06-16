package forumSegundoBimestre;

import com.fasterxml.jackson.databind.ObjectMapper;
import forumSegundoBimestre.Produto.Produto;
import forumSegundoBimestre.Produto.ProdutoController;
import forumSegundoBimestre.Produto.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@AutoConfigureMockMvc
class TestesComProduto {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoController controller;

    @MockBean
    private ProdutoService service;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testeGetByIdComDadoJaExistente() throws Exception {
        when(service.getById("2")).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/api/carros/2")).andExpect(status().isNotFound());
    }

    @Test
    void testeDeGetByIdComDadoExistente() throws Exception {
        Produto existente = new Produto("1", "NESCAU", "CEREAL RADICAL");
        when(service.getById("1")).thenReturn(existente);

        mockMvc.perform(get("/api/produtos/1"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nome").value("Nescau"))
                .andExpect(jsonPath("$.descricao").value("Cereal Radical"))
                .andExpect(status().isOk());
    }

    @Test
    void testeDeGetAllDeProduto() throws Exception {
        Produto a = new Produto("1", "Nescau1", "Cereal Radical 1");
        Produto b = new Produto("2", "Nescau2", "Cereal Radical 2");
        Produto c = new Produto("3", "Nescau3", "Cereal Radical 3");
        when(service.getAll()).thenReturn(Arrays.asList(a, b, c));

        mockMvc.perform(get("/api/carros"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[1].id").value("2"))
                .andExpect(jsonPath("$.[2].id").value("3"))
                .andExpect(jsonPath("$.[0].nome").value("Nescau1"))
                .andExpect(jsonPath("$.[1].nome").value("Nescau2"))
                .andExpect(jsonPath("$.[2].nome").value("Nescau3"))
                .andExpect(jsonPath("$.[0].descricao").value("Cereal Radical 1"))
                .andExpect(jsonPath("$.[1].descricao").value("Cereal Radical 2"))
                .andExpect(jsonPath("$.[2].descricao").value("Cereal Radical 3"))
                .andExpect(status().isOk());
    }

    @Test
    void testeDePostDeProduto() throws Exception {
        when(service.save(ArgumentMatchers.any(Produto.class))).thenReturn("50");

        Map<String, String> Produto = new HashMap<String, String>() {{
            put("id", "50");
            put("nome", "Nescau do Falcão");
            put("descricao", "Cereal do jogador de futsal");
        }};

        String ProdutoLikeJson = objectMapper.writeValueAsString(Produto);

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ProdutoLikeJson))
                .andExpect(status().isCreated())
                .andExpect(content().string("50"));
    }

    @Test
    void testeDeDeleteDeProduto() throws Exception {
        doNothing().when(service).deleteById("1");
        mockMvc.perform(delete("/api/produtos/1")).andExpect(status().isNoContent());
    }

    @Test
    void testeDePutIncorreto() throws Exception {
        when(service.save(ArgumentMatchers.any(Produto.class))).thenThrow(BadRequestException.class);

        Map<String, String> Produto = new HashMap<String, String>() {{
            put("id", "50");
            put("nome", "Nescau do Falcão");
            put("descricao", "Cereal do jogador de futsal");
        }};

        String ProdutoLikeJson = objectMapper.writeValueAsString(Produto);

        mockMvc.perform(put("/api/produtos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ProdutoLikeJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testeDePutDeuCerto() throws Exception {
        when(service.save(ArgumentMatchers.any(Produto.class))).thenReturn("50");

        Map<String, String> Produto = new HashMap<String, String>() {{
            put("id", "50");
            put("nome", "Nescau do falcão");
            put("descricao", "Nescau do jogador de futsal");
        }};

        String ProdutoLikeJson = objectMapper.writeValueAsString(Produto);

        mockMvc.perform(put("/api/produtos/50")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ProdutoLikeJson))
                .andExpect(status().isNoContent());
    }
}
