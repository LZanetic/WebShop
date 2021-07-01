package com.example.njava.proizvod;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;

@SpringBootTest
public class ProizvodServiceTest {

    @Autowired
    ProizvodService proizvodService;

    @MockBean
    ProizvodRepository proizvodRepository;

    Proizvod proizvod = new Proizvod(1L, "Predmet", "Opis", "Stanje",25, "Kategorija");
    List<Proizvod> proizvodList = new ArrayList<>();

    @Test
    @Transactional
    void findAll(){
        proizvodList.add(proizvod);


        when(proizvodRepository.findAll()).thenReturn(proizvodList);

        List<ProizvodDTO> proizvodList = proizvodService.findAll();
        Assertions.assertFalse(proizvodList.isEmpty());
    }

    @Test
    @Transactional
    void findById(){
        when(proizvodRepository.findByNaslov("Predmet")).thenReturn(proizvod);
        ProizvodDTO proizvodDTO = proizvodService.findByNaslov("Predmet");
        Assertions.assertEquals(proizvodDTO.getNaslov(), "Predmet");
    }
}
