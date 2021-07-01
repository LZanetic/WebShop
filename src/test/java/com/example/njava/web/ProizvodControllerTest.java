package com.example.njava.web;

import com.example.njava.proizvod.ProizvodDTO;
import com.example.njava.proizvod.ProizvodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
public class ProizvodControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProizvodService proizvodService;
    @Test
    void getAllProizvod_empty() throws Exception {
        when(proizvodService.findAll()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(
                get("/proizvod")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        )
                        .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getAllKorisnik() throws Exception {
        ProizvodDTO proizvodDTO = new ProizvodDTO(1L, "Predmet", "Opis", "stanje", 45, "Kat");
        List<ProizvodDTO> proizvodDTOList = new ArrayList<>();
        proizvodDTOList.add(proizvodDTO);

        when(proizvodService.findAll()).thenReturn(proizvodDTOList);

        this.mockMvc.perform(
                get("/proizvod")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        )
                        .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
