package com.example.njava.web;
import com.example.njava.korisnik.KorisnikDTO;
import com.example.njava.korisnik.KorisnikService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class KorisnikControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    KorisnikService korisnikService;
    @Test
    void getAllKorisnik_empty() throws Exception {
        when(korisnikService.findAll()).thenReturn(Collections.emptySet());

        this.mockMvc.perform(
                get("/korisnik")
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
        KorisnikDTO korisnik = new KorisnikDTO("Ime", "Prezime", "User", "kontakt", "lok", "0999895678");
        Set<KorisnikDTO> korisnikSet = new HashSet<>();
        korisnikSet.add(korisnik);


        when(korisnikService.findAll()).thenReturn(korisnikSet);

        this.mockMvc.perform(
                get("/korisnik")
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
