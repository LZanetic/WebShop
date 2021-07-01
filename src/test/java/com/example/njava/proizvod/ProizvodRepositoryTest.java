package com.example.njava.proizvod;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProizvodRepositoryTest {

    @Autowired
    ProizvodRepository proizvodRepository;

    @Test
    void findAll(){
        List<Proizvod> proizvodSet = proizvodRepository.findAll();

        Assertions.assertNotNull(proizvodSet);
        Assertions.assertEquals(proizvodSet.size(), 1);
    }

    @Test
    void findAllByKorisnik() {
        List<Proizvod> proizvodSet = proizvodRepository.findAllByKorisnikId(1L);

        Assertions.assertNotNull(proizvodSet);
        Assertions.assertEquals(proizvodSet.size(), 1);
    }

    @Test
    void findByNaslov() {
        Proizvod proizvod = proizvodRepository.findByNaslov("Predmet");

        Assertions.assertNotNull(proizvod);
        Assertions.assertTrue(proizvod.getNaslov().equals("Predmet"));
    }
}
