package com.example.njava.slike;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;

import java.util.*;

@SpringBootTest
public class SlikeServiceTest {

    @Autowired
    SlikeService slikeService;

    @MockBean
    SlikeRepository slikeRepository;

    @Test
    void findAll(){
        Slike slika = new Slike(1L, 1L, "slika" );
        List<Slike> slikeList = new ArrayList<>();
        slikeList.add(slika);


        when(slikeRepository.findAll()).thenReturn(slikeList);

        List<SlikeDTO> slikeDTOS = slikeService.findAll();
        Assertions.assertFalse(slikeDTOS.isEmpty());
    }

    @Test
    void findByProizvodId(){
        Slike slika = new Slike(1L, 1L, "slika");
        List<Slike> slikeList = new ArrayList<>();
        slikeList.add(slika);

        when(slikeRepository.findByProizvodId(1L)).thenReturn(slikeList);

        List<SlikeDTO> slikeDTO = slikeService.findByProizvodId(1L);

        Assertions.assertFalse(slikeDTO.isEmpty());
    }
}
