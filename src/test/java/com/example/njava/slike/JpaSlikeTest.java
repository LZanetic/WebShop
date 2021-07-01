package com.example.njava.slike;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JpaSlikeTest {

    @Autowired
    SlikeRepository slikeRepository;

    @Test
    public void FindAll(){
        List<Slike> slikeList = slikeRepository.findAll();
        Assertions.assertNotNull(slikeList);
    }

    @Test
    public void findByProizvodKorisnikId(){
        List<Slike> slikeList =
                slikeRepository.findByProizvodKorisnikId(1L);
        Assertions.assertNotNull(slikeList);
        Assertions.assertEquals(slikeRepository.findByProizvodKorisnikId(1L), slikeList);
    }

    @Test
    public void deleteByProizvodId(){
        slikeRepository.deleteByProizvodId(1L);

        List<Slike> slike =  slikeRepository.findByProizvodId(1L);
        Assertions.assertTrue(slike.isEmpty());
    }
}
