package com.example.njava.korisnikTest;


import com.example.njava.korisnik.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class KorisnikServiceTest {

    @Autowired
    KorisnikService korisnikService;

    @MockBean
    KorisnikRepository korisnikRepository;

    @MockBean
    UserRepository userRepository;

    Korisnik kor = new Korisnik(1L, "Ime", "Prezime", "testUsername", "pass", "kontakt", "lokacija", "mail", "099859854");

    Set<Korisnik> korisnikList = new HashSet<>();

    @Test
    @Transactional
    void findAll(){
        korisnikList.add(kor);


        when(korisnikRepository.findAll()).thenReturn(korisnikList);

        Set<KorisnikDTO> korisnikDTOSet = korisnikService.findAll();
        Assertions.assertFalse(korisnikDTOSet.isEmpty());
    }

    @Test
    @Transactional
    void findByUsername(){

        when(userRepository.findOneByUsername("testUsername")).thenReturn(
                Optional.of(new Korisnik(1L, "Ime", "Prezime", "testUsername", "pass", "kontakt", "lokacija", "mail", "099859854"))
        );

        KorisnikDTO korisnikDTO = korisnikService.findByUsername("testUsername").orElse(null);


        Assertions.assertNotNull(korisnikDTO);
        Assertions.assertEquals(korisnikDTO.getIme(), "Ime" );
        Assertions.assertEquals(korisnikDTO.getPrezime(), "Prezime");
        Assertions.assertEquals(korisnikDTO.getUsername(), "testUsername");
        Assertions.assertEquals(korisnikDTO.getKontakt(), "kontakt");
        Assertions.assertEquals(korisnikDTO.getLokacija(), "lokacija");
        Assertions.assertEquals(korisnikDTO.getBrojTelefona(), "099859854");
    }


    @Test
    void deleteByUsername(){
        korisnikRepository.save(new Korisnik(3, "test", "test", "test", "$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy", "test", "test", "test", "test"));
        korisnikService.deleteByUsername("test");
        
        verify(korisnikRepository).deleteByUsername("test");
    }
}
