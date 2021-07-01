package com.example.njava.korisnikTest;


import com.example.njava.korisnik.Korisnik;
import com.example.njava.korisnik.KorisnikRepository;
import com.example.njava.proizvod.ProizvodRepository;
import com.example.njava.slike.SlikeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class JdbcKorisnikRepositoryTest{

    @Autowired
    KorisnikRepository korisnikRepository;

    @Autowired
    ProizvodRepository proizvodRepository;

    @Autowired
    SlikeRepository slikeRepository;

    @Test
    void findAll(){
        Set<Korisnik> korisnici = korisnikRepository.findAll();
        Assertions.assertFalse(korisnici.isEmpty());
    }

    @Test
    void save(){
        Korisnik noviKorisnik = new Korisnik(3, "test", "test", "test", "$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy", "test", "test", "test", "test");
        korisnikRepository.save(noviKorisnik);
        Optional<Korisnik> provjeraKorisnika = Optional.of(korisnikRepository.findByUsername("test").orElse(null));
        Assertions.assertNotNull(provjeraKorisnika);
    }

    @Test
    void update(){
        Korisnik noviKorisnik = new Korisnik(3, "test", "test", "test", "$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy", "test", "test", "test", "test");
        korisnikRepository.save(noviKorisnik);
        Korisnik updateKorisnika = new Korisnik(3, "Pero", "Peric", "test", "$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy", "test", "test", "test", "test");
        korisnikRepository.update("test", updateKorisnika);
        Optional<Korisnik> provjeraKorisnika = Optional.of(korisnikRepository.findByUsername("test").orElse(null));
        Assertions.assertNotNull(provjeraKorisnika);
        Assertions.assertAll(
                () -> Assertions.assertEquals(provjeraKorisnika.get().getIme(), "Pero"),
                () -> Assertions.assertEquals(provjeraKorisnika.get().getPrezime(), "Peric")
        );
    }

    @Test
    void findByUsername(){
        Korisnik korisnik = korisnikRepository.findByUsername("user").orElse(null);

        Assertions.assertNotNull(korisnik);

        Assertions.assertAll(
                ()-> Assertions.assertEquals(korisnik.getIme(), "Ime2"),
                ()-> Assertions.assertEquals(korisnik.getPrezime(), "prezime2"),
                ()-> Assertions.assertEquals(korisnik.getUsername(), "user"),
                ()-> Assertions.assertEquals(korisnik.getPassword(), "$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy"),
                ()-> Assertions.assertEquals(korisnik.getKontakt(), "09954785858"),
                ()-> Assertions.assertEquals(korisnik.getLokacija(), "lok"),
                ()-> Assertions.assertEquals(korisnik.getEmail(), "mail"),
                ()-> Assertions.assertEquals(korisnik.getBrojTelefona(), "0365848558")
                );
    }

    @Transactional
    @DirtiesContext
    @Test
    void deleteByUsername(){
        korisnikRepository.deleteByUsername("test");

        Korisnik korisnik = korisnikRepository.findByUsername("test").orElse(null);

        Assertions.assertNull(korisnik);
    }


}
